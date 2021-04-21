package com.osyangxin.dao.model;

import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 商品类目表
    */
public class ProductCategoryInfo {
    /**
    * id
    */
    private Long id;

    /**
    * 类目名称
    */
    private String name;

    /**
    * 类目描述
    */
    private String description;

    /**
    * 排序号
    */
    private Integer ranking;

    /**
    * 状态
    */
    private Short status;

    /**
    * 类目层级
    */
    private Short level;

    /**
    * 上级目录id
    */
    private Long originId;

    /**
    * 类目图片
    */
    private String image;

    /**
    * 类目图片(小)
    */
    private String mobileLogoSmall;

    /**
    * 类目图片(大)
    */
    private String mobileLogoBig;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobileLogoSmall() {
        return mobileLogoSmall;
    }

    public void setMobileLogoSmall(String mobileLogoSmall) {
        this.mobileLogoSmall = mobileLogoSmall;
    }

    public String getMobileLogoBig() {
        return mobileLogoBig;
    }

    public void setMobileLogoBig(String mobileLogoBig) {
        this.mobileLogoBig = mobileLogoBig;
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