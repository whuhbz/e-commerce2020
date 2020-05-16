package com.whu.api.service;

import com.whu.api.bean.PmsBaseAttrInfo;
import com.whu.api.bean.PmsBaseAttrValue;

import java.util.List;

public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(Integer id);
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);
    List<PmsBaseAttrValue> getAttrValueList(Integer id);
}
