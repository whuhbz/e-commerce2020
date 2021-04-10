package com.whu.api.service;

import com.whu.api.bean.DemoUser;
import com.whu.api.bean.Member;

public interface UserService {
    DemoUser selectUser(String id);

    Member login(Member loginMember);

    void addToken(String token, Integer memberId);
}
