package com.whu.user.service;

import com.whu.user.bean.DemoUser;

public interface UserService {
    DemoUser selectUser(String id);
}
