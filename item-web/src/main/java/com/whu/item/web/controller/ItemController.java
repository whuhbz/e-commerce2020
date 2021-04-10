package com.whu.item.web.controller;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {
    @RequestMapping("index")
    public String index(ModelMap modelMap){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("ss"+i);
        }
        modelMap.put("list",list);
        modelMap.put("hello","dfs");
        return "index";
    }
    static void test(StringBuilder stringBuilder){
        stringBuilder.append("dddff");
        stringBuilder = new StringBuilder("ccc");
    }
    @Test
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("bbb");
        test(stringBuilder);
        System.out.println(stringBuilder);
    }
}
