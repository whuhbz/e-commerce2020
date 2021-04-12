package com.whu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whu.api.bean.OmsCartItem;
import com.whu.api.service.CartService;
import com.whu.mapper.CartMapper;
import com.whu.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, OmsCartItem> implements CartService {



    @Autowired
    CartMapper cartMapper;
    @Autowired
    RedisUtil redisUtil;



    @Override
    public OmsCartItem getCartExistByUser(String memberId, String skuId) {
        return cartMapper.selectOne(new QueryWrapper<OmsCartItem>().eq("member_Id",memberId).eq("product_sku_id",skuId));
    }

    @Override
    public void addCartIterm(OmsCartItem omsCartItem) {
        cartMapper.insert(omsCartItem);
    }

    @Override
    public void updateCarItem(OmsCartItem omsCartItemFromDB) {
        cartMapper.update(omsCartItemFromDB,new UpdateWrapper<OmsCartItem>().eq("id",omsCartItemFromDB.getId()));
    }

    @Override
    public void flushCache(String memberId) {
        List<OmsCartItem> omsCartItems = cartMapper.selectList(new QueryWrapper<OmsCartItem>().eq("member_id",memberId));
        //同步到redis缓存
        Map<String,String> map = new HashMap<>();
        for (OmsCartItem omsCartItem : omsCartItems) {
            map.put(omsCartItem.getProductSkuId().toString(), JSON.toJSONString(omsCartItem));
        }
        Jedis jedis = redisUtil.getJedis();
        try {
            jedis.del("user:"+memberId+":cart");
            jedis.hmset("user:"+memberId+":cart",map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

    @Override
    public List<OmsCartItem> carList(String userId) {
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        //在缓存中查询
        Jedis jedis=null;
        try{
            jedis = redisUtil.getJedis();
            List<String> hvals = jedis.hvals("user:" + userId + ":cart");
        for (String hval : hvals) {
            OmsCartItem omsCartItem = JSON.parseObject(hval, OmsCartItem.class);
            omsCartItems.add(omsCartItem);
        }
            }catch (Exception e){
                 e.printStackTrace();
                 return null;
              }finally {
            jedis.close();
        }

        //缓存中有数据
        //缓存中没有数据，去数据库中查询（分布式锁），再放入缓存

        return omsCartItems;
    }

    @Override
    public void checkCart(String skuId, String memberId, String isChecked) {
        OmsCartItem omsCartItem = new OmsCartItem();
        omsCartItem.setIsChecked(isChecked);
        cartMapper.updateIsChecked(skuId,memberId,isChecked);
        //缓存刷新
        try{
            flushCache(memberId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
