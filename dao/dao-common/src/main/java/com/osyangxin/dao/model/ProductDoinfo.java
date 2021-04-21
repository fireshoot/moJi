package com.osyangxin.dao.model;

import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 商品文档表
    */
public class ProductDoinfo {
    /**
    * id
    */
    private Long id;

    /**
    * 商品id
    */
    private Long productId;

    /**
    * 商品编码
    */
    private String productCode;

    /**
    * 文档链接地址
    */
    private String url;

    /**
    * 描述信息
    */
    private String thumbnailDescription;

    /**
    * 排序号
    */
    private Integer ranking;

    /**
    * 是否默认图片
    */
    private Boolean isDefault;

    /**
    * 是否删除
    */
    private Boolean isDeleted;

    /**
    * 添加时间
    */
    private Date addDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailDescription() {
        return thumbnailDescription;
    }

    public void setThumbnailDescription(String thumbnailDescription) {
        this.thumbnailDescription = thumbnailDescription;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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
}