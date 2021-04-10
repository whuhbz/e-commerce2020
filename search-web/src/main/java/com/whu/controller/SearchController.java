package com.whu.controller;

import com.whu.annotations.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class SearchController {
    @RequestMapping("/index")
    @LoginRequired(loginSuccess = false)
    public String index(){
        return "index";
    }
}
