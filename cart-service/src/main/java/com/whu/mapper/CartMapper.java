package com.whu.mapper;

import com.whu.api.bean.OmsCartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author LiangHuan
 * @since 2020-04-30
 */
@Mapper
public interface CartMapper extends BaseMapper<OmsCartItem> {

    void updateIsChecked(@Param("skuId") String skuId, @Param("memberId") String memberId, @Param("isChecked") String isChecked);
}
