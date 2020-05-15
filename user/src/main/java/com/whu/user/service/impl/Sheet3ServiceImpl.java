package com.whu.user.service.impl;

import com.whu.user.bean.Sheet3;
import com.whu.user.mapper.Sheet3Mapper;
import com.whu.user.service.Sheet3Service;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
@Service
public class Sheet3ServiceImpl implements Sheet3Service {

    @Resource
    Sheet3Mapper sheet3Mapper;

    @Override
    public List<Sheet3> getUserNativePlace(String str){
        Example e = new Example(Sheet3.class);
        e.createCriteria().andEqualTo("nativeplace",str);
        List<Sheet3> result = sheet3Mapper.selectByExample(e);
        //Sheet3 sheet3 = new Sheet3();
        //sheet3.setId(id);
        //List<Sheet3> result = sheet3Mapper.select(sheet3);
        return result;
    }
}
