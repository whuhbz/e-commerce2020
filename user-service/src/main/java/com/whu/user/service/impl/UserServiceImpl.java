package com.whu.user.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whu.api.bean.DemoUser;
import com.whu.api.bean.Member;
import com.whu.user.mapper.DemoUserMapper;
import com.whu.api.service.UserService;
import com.whu.user.mapper.UserMapper;
import com.whu.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    //测试开始
    @Resource
    DemoUserMapper demoUserMapper;
    @Override
    public DemoUser selectUser(String id){
        DemoUser user = null;//demoUserMapper.selectByPrimaryKey(id);
        return user;
    }
    //测试end

    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserMapper userMapper;
    @Override
    public Member login(Member loginMember) {
        Jedis jedis = null;
        try{
            jedis = redisUtil.getJedis();
            if(jedis != null){
                String s = jedis.get("user:" + loginMember.getPassword() + ":info");
                if(StringUtils.isNotBlank(s)){
                    //密码正确
                    Member member = JSON.parseObject(s, Member.class);
                    return member;
                }else{
                    //密码错误
                    //缓存中没有
                    //开启数据库
                    Member memberFromDb = loginFromDb(loginMember);
                    if(memberFromDb != null){
                        jedis.setex("user:"+loginMember.getPassword()+":info",60*60*24,JSON.toJSONString(memberFromDb));
                    }
                    return memberFromDb;
                }
            }else{
                //缓存不可用，开启数据库
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }


        return null;
    }

    private Member loginFromDb(Member loginMember) {
        Member o = userMapper.selectOne(new QueryWrapper<Member>().eq("username",loginMember.getUsername()).eq("password",loginMember.getPassword()));
        return o;
    }

    @Override
    public void addToken(String token, Integer memberId) {
        Jedis jedis = redisUtil.getJedis();
        jedis.setex("user:"+memberId+":token",60*60*2,token);
        jedis.close();
    }
}
