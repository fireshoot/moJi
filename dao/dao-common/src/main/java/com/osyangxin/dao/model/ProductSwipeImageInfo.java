package com.osyangxin.dao.model;

import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 商品轮播图表
    */
public class ProductSwipeImageInfo {
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
    * 图片链接地址
    */
    private String url;

    /**
    * 縮略图片链接地址
    */
    private String thumbnailUrl;

    /**
    * 图片描述信息
    */
    private String imageDescription;

    /**
    * 縮略图片描述信息
    */
    private String thumbnailDescription;

    /**
    * 图片排序号
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
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