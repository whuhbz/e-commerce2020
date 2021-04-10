package com.whu.api.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whu.api.bean.OmsCartItem;
import java.util.List;

public interface CartService extends IService<OmsCartItem> {

    /**
     * 查询用户购物车中是否有该商品
     * @param memberId
     * @param skuId
     * @return
     */
    OmsCartItem getCartExistByUser(String memberId, String skuId);

    /**
     * 添加商品信息到购物车
     * @param omsCartItem
     */
    void addCartIterm(OmsCartItem omsCartItem);

    /**
     * 更新购物车中的该商品信息
     * @param omsCartItemFromDB
     */
    void updateCarItem(OmsCartItem omsCartItemFromDB);


    /**
     * 刷新缓存
     * @param memberId
     */
    void flushCache(String memberId);

    /**
     * 根据userId查询购物车
     * @param userId
     * @return
     */
    List<OmsCartItem> carList(String userId);

    void checkCart(String skuId,String memberId, String isChecked);
}
