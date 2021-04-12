package com.whu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.api.bean.OmsCartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @Description:
 * @Author: hbz
 * @Date: 2021/4/12 9:36
 */
@Mapper
public interface CartMapper extends BaseMapper<OmsCartItem> {

    void updateIsChecked(@Param("skuId") String skuId, @Param("memberId") String memberId, @Param("isChecked") String isChecked);
}
