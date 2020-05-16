package com.whu.api.service;

import com.whu.api.bean.PmsBaseCatalog1;
import com.whu.api.bean.PmsBaseCatalog2;
import com.whu.api.bean.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {

    List<PmsBaseCatalog3> getCatalog3(Integer id);

    List<PmsBaseCatalog2> getCatalog2(Integer id);

    List<PmsBaseCatalog1> getCatalog1();
}
