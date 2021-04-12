package com.whu.controller;

import com.alibaba.fastjson.JSON;
import com.whu.api.bean.Member;
import com.whu.api.service.UserService;
import com.whu.util.JwtUtil;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class PassportController {
    @Reference
    UserService userService;

    @RequestMapping("/verify")
    @ResponseBody
    public String verify(String token,String currentIp){
        //通过jwt校验token真假
        Map<String,String> map = new HashMap<>();
        Map<String, Object> decode = null;
        if(StringUtils.isNotBlank(token)&&StringUtils.isNotBlank(currentIp)){
            decode = JwtUtil.decode(token, "whuinfo", currentIp);
        }
        if(decode!=null){
            map.put("status","success");
            map.put("memberId",decode.get("memberId").toString());
            map.put("nickName",decode.get("nickName").toString());
        }else{
            map.put("status","fail");
        }
        ArrayList<String> strings = new ArrayList<>(map.values());
        String[] list = (String[]) strings.toArray();
                Arrays.sort(strings.toArray());
        return JSON.toJSONString(map);
    }

    @RequestMapping("/index")
    public String index(String ReturnUrl, ModelMap map){
        map.put("ReturnUrl", ReturnUrl);
        return "index";
    }
    @ResponseBody
    @RequestMapping("/login")
    public String login(Member loginMember, HttpServletRequest request){
        String token = "fail";
        Member member = userService.login(loginMember);
        if(member != null){
            //登录成功，用jwt制作token
            Integer memberId = member.getId();
            String nickName = member.getNickname();
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("memberId", memberId);
            userMap.put("nickName", nickName);
            String ip = request.getHeader("x-forwarded-for");//通过nginx转发的获得的用户ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();

            }
            token = JwtUtil.encode("whuinfo",userMap,ip);
            userService.addToken(token,memberId);
        }
        return  token;
    }
}
