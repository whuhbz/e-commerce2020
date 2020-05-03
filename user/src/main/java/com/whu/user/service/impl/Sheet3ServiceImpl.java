package com.whu.user.service.impl;

import com.whu.user.bean.Sheet3;
import com.whu.user.mapper.Sheet3Mapper;
import com.whu.user.service.Sheet3Service;
import io.swagger.annotations.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class Sheet3ServiceImpl implements Sheet3Service {

    @Resource
    Sheet3Mapper sheet3Mapper;

    @Override
    public List<Sheet3> getUserNativePlace(int id){
        //Example e = new Example(Sheet3.class)
        Sheet3 sheet3 = new Sheet3();
        sheet3.setId(id);
        List<Sheet3> result = null;//sheet3Mapper.select(sheet3);
        return result;
    }
}
