package com.whu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtil {
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try{
            for (int i = 0; i < cookies.length; i++) {
                if(cookies[i].getName().equals(cookieName)){
                    if(isDecoder){//如果涉及中文
                        retValue = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
                    }else{
                        retValue = cookies[i].getValue();
                    }
                }
            }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return retValue;
    }
}
