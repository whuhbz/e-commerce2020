package com.whu.api.service;


import com.whu.api.bean.PmsSkuInfo;

import java.math.BigDecimal;
import java.util.List;

public interface SkuService {

    /**
     * 保存商品库存信息
     * @param pmsSkuInfo
     */
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    /**
     * 根据库存id获取库存信息
     * @param skuId
     * @return
     */
    PmsSkuInfo getSkuById(String skuId);

    /**
     * 根据spuId来获取对应的sku集合和sku集合内的对应的属性
     * @param spuId
     * @return
     */
    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String spuId);


    /**
     * 查询所有sku的信息
     */
    List<PmsSkuInfo> selectAllSku(String catalog3Id);


    String getSkuPriceBySkuId(Long productSkuId);

    boolean checkPrice(Long productSkuId, BigDecimal price);

}
