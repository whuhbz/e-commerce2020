package com.whu.user.controller;

import com.whu.user.bean.DemoUser;
import com.whu.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags="user测试")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("查询某个用户")
    @RequestMapping(value = "selectUser",method= RequestMethod.GET)
    @ResponseBody
    public DemoUser selectUser(@RequestParam String id){
        DemoUser user = userService.selectUser(id);
        return user;
    }
    @ApiOperation("index方法")
    @RequestMapping(value = "index",method= RequestMethod.POST)
    @ResponseBody
    public String index(@RequestBody String str){
        return "rest";
    }

    @ApiOperation("测试")
    @RequestMapping(value = "/",method= RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public DemoUser test(@RequestParam String name, @RequestBody DemoUser user){
        return new DemoUser();
    }
}
