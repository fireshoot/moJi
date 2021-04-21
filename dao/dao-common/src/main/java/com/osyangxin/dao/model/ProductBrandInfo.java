package com.osyangxin.dao.model;

import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 商品品牌信息表
    */
public class ProductBrandInfo {
    private Long id;

    /**
    * 品牌 名称
    */
    private String brand;

    /**
    * 是否删除
    */
    private Boolean isDeleted;

    /**
    * 添加时间
    */
    private Date addDt;

    /**
    * 修改时间
    */
    private Date updateDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddDt() {
        return addDt;
    }

    public void setAddDt(Date addDt) {
        this.addDt = addDt;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
}