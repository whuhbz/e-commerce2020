package com.whu.api.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class PmsProductInfo implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String productName;

    @Column
    private String description;

    @Column
    private Integer catalog3Id;

    @Column
    private Integer tmId;

    @Transient
    private List<PmsProductSaleAttr> spuSaleAttrList;

    @Transient
    private List<PmsProductImage> spuImageList;

    public List<PmsProductSaleAttr> getSpuSaleAttrList() {
        return spuSaleAttrList;
    }

    public void setSpuSaleAttrList(List<PmsProductSaleAttr> spuSaleAttrList) {
        this.spuSaleAttrList = spuSaleAttrList;
    }

    public List<PmsProductImage> getSpuImageList() {
        return spuImageList;
    }

    public void setSpuImageList(List<PmsProductImage> spuImageList) {
        this.spuImageList = spuImageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCatalog3Id() {
        return catalog3Id;
    }

    public void setCatalog3Id(Integer catalog3Id) {
        this.catalog3Id = catalog3Id;
    }

    public Integer getTmId() {
        return tmId;
    }

    public void setTmId(Integer tmId) {
        this.tmId = tmId;
    }
}