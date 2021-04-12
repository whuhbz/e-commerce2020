package com.whu.controller;

import com.whu.annotations.LoginRequired;
import com.whu.api.bean.PmsSkuInfo;
import com.whu.api.service.CartService;
import com.whu.api.service.SkuService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    @Reference
    CartService cartService;
    @Reference
    SkuService skuService;
    @LoginRequired(loginSuccess = true)
    @RequestMapping("/toTrade")
    public String toTrade(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap){
        String memberId = (String)request.getAttribute("memberId");
        String nickname = (String)request.getAttribute("nickname");
        return "toTrade";
    }
    @LoginRequired(loginSuccess = false)
    @RequestMapping("/addToCart")
    public String addToCart(String skuId, int quantity, HttpServletRequest request, HttpServletResponse response){
        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);//查询商品信息
        return null;
    }
}
