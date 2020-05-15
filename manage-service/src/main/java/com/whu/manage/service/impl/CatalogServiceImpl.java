package com.whu.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whu.api.bean.PmsBaseCatalog1;
import com.whu.api.service.CatalogService;
import com.whu.manage.mapper.PmsBaseCatalog1Mapper;
import com.whu.manage.mapper.PmsBaseCatalog2Mapper;
import com.whu.manage.mapper.PmsBaseCatalog3Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;

    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }
}
