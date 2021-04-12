package com.whu.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whu.api.bean.PmsSkuAttrValue;
import com.whu.api.bean.PmsSkuImage;
import com.whu.api.bean.PmsSkuInfo;
import com.whu.api.bean.PmsSkuSaleAttrValue;
import com.whu.api.service.SkuService;
import com.whu.manage.mapper.PmsSkuAttrValueMapper;
import com.whu.manage.mapper.PmsSkuImageMapper;
import com.whu.manage.mapper.SkuInfoMapper;
import com.whu.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.whu.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    Redisson redisson;
    @Autowired
    RedisUtil redisUtil;


    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {

        // 插入skuInfo
        skuInfoMapper.insert(pmsSkuInfo);
        String skuId = pmsSkuInfo.getId();

        // 插入平台属性关联
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuId);
            pmsSkuAttrValueMapper.insert(pmsSkuAttrValue);
        }

        // 插入销售属性关联
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuId);
            pmsSkuSaleAttrValueMapper.insert(pmsSkuSaleAttrValue);
        }

        // 插入图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : skuImageList) {
            pmsSkuImage.setSkuId(skuId);
            pmsSkuImageMapper.insert(pmsSkuImage);
        }


    }


    public PmsSkuInfo getSkuByIdFromDb(String skuId){
        // sku商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = skuInfoMapper.selectOne(new QueryWrapper<PmsSkuInfo>().eq("id", skuId));

        // sku的图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.selectList(new QueryWrapper<PmsSkuImage>().eq("sku_id", skuId));
        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {

        List<PmsSkuInfo> pmsSkuInfos = skuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);

        return pmsSkuInfos;
    }

    @Override
    public List<PmsSkuInfo> selectAllSku(String catalog3Id) {
        List<PmsSkuInfo> pmsSkuInfos = skuInfoMapper.selectList(new QueryWrapper<PmsSkuInfo>());
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            String skuId = pmsSkuInfo.getId();
            List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuAttrValueMapper.selectList(new QueryWrapper<PmsSkuAttrValue>().eq("sku_id", skuId));
            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValueList);
        }
        return pmsSkuInfos;
    }

    @Override
    public String getSkuPriceBySkuId(Long productSkuId) {
        PmsSkuInfo pmsSkuInfo = skuInfoMapper.selectById(productSkuId);
        return pmsSkuInfo.getPrice().toString();
    }

    @Override
    public boolean checkPrice(Long productSkuId, BigDecimal price) {
        boolean b =false;
        BigDecimal skuPriceBySkuId = new BigDecimal(String.valueOf(skuInfoMapper.selectById(productSkuId).getPrice()));
        if(skuPriceBySkuId.compareTo(price)==0){
            b = true;
        }
        return b;
    }


    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        PmsSkuInfo pmsSkuInfo = null;
        Jedis jedis = redisUtil.getJedis();
        //查询缓存
        String skuKey = "sku:"+skuId+":info";
        String skuJson = jedis.get(skuKey);
        try{
            if(StringUtils.isNotBlank(skuJson)){
                pmsSkuInfo = JSON.parseObject(skuJson,PmsSkuInfo.class);
            }else{
                //没有缓存则查询数据库
                //设置分布式锁
                String token = UUID.randomUUID().toString();
                //第三个参数nxxx: nx -> not exists, 只有key 不存在时才把key value set 到redis
                //              xx -> is exists ，只有 key 存在是，才把key value set 到redis
                //第四个参数expx: ex -> seconds 秒, px -> milliseconds 毫秒
                //返回值String，如果写入成功是“OK”，写入失败返回空
                String OK = jedis.set("sku:" + skuId + ":lock", token, "nx", "px", 10 * 1000);//10秒
                if(StringUtils.isNotBlank(OK) && OK.equals("OK")){
                    pmsSkuInfo = skuInfoMapper.selectById(skuId);
                    List<PmsSkuImage> skuImageList = pmsSkuImageMapper.selectList(new QueryWrapper<PmsSkuImage>().eq("sku_id", skuId));
                    pmsSkuInfo.setSkuImageList(skuImageList);
                    //mysql结果存入redis
                    if(pmsSkuInfo != null){
                        jedis.set("sku:"+skuId+":info",JSON.toJSONString(pmsSkuInfo));
                    }else{
                        //数据中不存在该sku，为了防止缓存穿透，null或空字符串设置给redis
                        jedis.setex("sku:"+skuId+":info",60*3,JSON.toJSONString(""));
                    }
                    //在访问mysql之后，将分布式锁释放
                    String lockToken = jedis.get("sku:"+skuId+":lock");
                    if(StringUtils.isNotBlank(lockToken) && lockToken.equals(token)){
                        //用token确认删除的是自己的锁
                        //jedis.eval("lua"):可以用lua脚本，在查询key的同时删除该key，防止高并发下的意外发生
                        jedis.del("sku:"+skuId+":lock");
                    }
                }else{
                    //设置失败自旋，睡眠几秒后，再重新尝试访问
                    try {
                        Thread.sleep(3000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return getSkuById(skuId);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return pmsSkuInfo;
    }
}
