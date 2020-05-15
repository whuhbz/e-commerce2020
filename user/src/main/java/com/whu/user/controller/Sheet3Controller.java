package com.whu.user.controller;

import com.whu.user.bean.Sheet3;
import com.whu.user.service.Sheet3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "sheet3测试")
public class Sheet3Controller {
    @Autowired
    Sheet3Service sheet3Service;

    @ApiOperation("查询某个用户")
    @RequestMapping(value = "selectSheet3User",method= RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public List<Sheet3> selectSheet3User(@RequestParam String str){
        List<Sheet3> users = sheet3Service.getUserNativePlace(str);
        return users;
    }
}
