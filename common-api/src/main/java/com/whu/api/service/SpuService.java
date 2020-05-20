package com.whu.api.service;

import com.whu.api.bean.PmsProductImage;
import com.whu.api.bean.PmsProductInfo;
import com.whu.api.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductInfo> spuList(Integer catalog3Id);

    List<PmsProductSaleAttr> spuSaleAttrList(Integer spuId);

    List<PmsProductImage> spuImageList(Integer spuId);
}
