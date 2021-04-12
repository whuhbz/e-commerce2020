package com.whu.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.api.bean.PmsSkuInfo;

import java.util.List;

public interface SkuInfoMapper extends BaseMapper<PmsSkuInfo> {
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
