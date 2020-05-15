package com.whu.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.whu.api.bean.DemoUser;
import com.whu.user.mapper.DemoUserMapper;
import com.whu.api.service.UserService;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    DemoUserMapper demoUserMapper;

    @Override
    public DemoUser selectUser(String id){
        DemoUser user = null;//demoUserMapper.selectByPrimaryKey(id);
        return user;
    }
}
