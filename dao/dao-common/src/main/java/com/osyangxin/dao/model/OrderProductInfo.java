package com.osyangxin.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 订单商品表
    */
public class OrderProductInfo {
    private Long id;

    /**
    * 订单表 id
    */
    private Long orderId;

    /**
    * 商品 id
    */
    private String productId;

    /**
    * 商品 code 
    */
    private String productCode;

    /**
    * 商品名称 
    */
    private String productName;

    /**
    * 商品缩略图
    */
    private String productThumbnailUrl;

    /**
    * 商品数量
    */
    private Integer productCount;

    /**
    * 商品成本价
    */
    private BigDecimal productCostPrice;

    /**
    * 商品单价
    */
    private BigDecimal productPrice;

    /**
    * 商品数据大字段
    */
    private String productInfo;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductThumbnailUrl() {
        return productThumbnailUrl;
    }

    public void setProductThumbnailUrl(String productThumbnailUrl) {
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getProductCostPrice() {
        return productCostPrice;
    }

    public void setProductCostPrice(BigDecimal productCostPrice) {
        this.productCostPrice = productCostPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
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