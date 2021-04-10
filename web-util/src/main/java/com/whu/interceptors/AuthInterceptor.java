package com.whu.interceptors;

import com.whu.annotations.LoginRequired;
import com.whu.util.CookieUtil;
import com.whu.util.HttpclientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //拦截代码
        //判断被拦截的请求的访问的方法的注解是否是需要拦截的
        HandlerMethod hm = (HandlerMethod) handler;
        LoginRequired methodAnnotation = hm.getMethodAnnotation(LoginRequired.class);
        //是否拦截
        if(methodAnnotation == null){
            return true;
        }
        /**
         * 如果oldToken为null newToken为null 说明从未登lu过
         * newToken为null oldToken不为null之前登录过
         * newToken不为null oldToken为null刚刚登录过
         * newToken不为空   oldToken不为空  oldToken过期
         */
        String token = "";
        String oldToken = CookieUtil.getCookieValue(request,"oldToken",true);
        if(StringUtils.isNotBlank(oldToken)){
            token = oldToken;
        }
        String newToken = request.getParameter("token");
        if(StringUtils.isNotBlank(newToken)){
            token = newToken;
        }
        //调用认证中心进行验证
        String success = HttpclientUtil.doGet("http://localhost:8085/verify?token="+token);
        //是否必须登录
        boolean loginSuccess = methodAnnotation.loginSuccess();
        if(loginSuccess){

        }else{

        }
        System.out.println("进入拦截器的拦截方法");
        return true;
    }
}
