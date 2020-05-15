package com.whu.api.service;

import com.whu.api.bean.DemoUser;

public interface UserService {
    DemoUser selectUser(String id);
}
