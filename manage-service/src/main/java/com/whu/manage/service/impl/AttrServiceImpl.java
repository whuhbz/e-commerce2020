package com.whu.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whu.api.bean.PmsBaseAttrInfo;
import com.whu.api.bean.PmsBaseAttrValue;
import com.whu.api.bean.PmsBaseSaleAttr;
import com.whu.api.service.AttrService;
import com.whu.manage.mapper.PmsBaseAttrInfoMapper;
import com.whu.manage.mapper.PmsBaseAttrValueMapper;
import com.whu.manage.mapper.PmsBaseSaleAttrMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(Integer catalog3Id){
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);

        for(PmsBaseAttrInfo baseAttrInfo: pmsBaseAttrInfos){
            List<PmsBaseAttrValue> pmsBaseAttrValues = new ArrayList<>();
            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }

        return  pmsBaseAttrInfos;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(Integer attrId){
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
        return  pmsBaseAttrValues;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo){

        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();

        Integer id = pmsBaseAttrInfo.getId();
        if(id == null){
            //id为空，新增
            //保存属性
            pmsBaseAttrInfoMapper.insert(pmsBaseAttrInfo);
            //保存属性值
            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList){
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
            }
        }else{
            //id不空，修改
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoMapper.updateByExample(pmsBaseAttrInfo,example);

            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);

            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList){
                pmsBaseAttrValueMapper.insert(pmsBaseAttrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        return pmsBaseSaleAttrMapper.selectAll();
    }
}
