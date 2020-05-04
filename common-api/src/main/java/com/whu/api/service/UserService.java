package com.whu.api.service;


import com.whu.api.bean.DemoUser;

public interface UserService {
    public DemoUser selectUser(String id);
}
