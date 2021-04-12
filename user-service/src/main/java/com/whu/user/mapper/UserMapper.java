package com.whu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.api.bean.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<Member> {
    List<Member> SelectUserById(Integer i);
}
