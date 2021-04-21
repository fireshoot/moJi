package com.osyangxin.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 商品信息
    */
public class ProductInfo {
    /**
    * id
    */
    private Long id;

    /**
    * 商品名称
    */
    private String name;

    /**
    * 促销标题
    */
    private String promotionTitle;

    /**
    * 计量单位
    */
    private String unit;

    /**
    * 商品编码
    */
    private String code;

    /**
    * 商品介绍
    */
    private String introduction;

    /**
    * 演示地址
    */
    private String demoUrl;

    /**
    * 商品缩略图
    */
    private String thumbnailUrl;

    /**
    * 商品描述
    */
    private String description;

    /**
    * 一级分类
    */
    private Long firstCategory;

    /**
    * 二级分类
    */
    private Long secondCategory;

    /**
    * 三级分类
    */
    private Long thirdCategory;

    /**
    * 进货价
    */
    private BigDecimal purchasePrice;

    /**
    * 售价
    */
    private BigDecimal salePrice;

    /**
    * 市场价
    */
    private BigDecimal marketPrice;

    /**
    * 服务费比例
    */
    private Integer serviceCharges;

    /**
    * 库存
    */
    private Integer stocks;

    /**
    * 销售数量
    */
    private Integer soldCount;

    /**
    * 预设销量
    */
    private Integer defaultSoldCount;

    /**
    * 商品类型
    */
    private Integer type;

    /**
    * 商品规格
    */
    private String specification;

    /**
    * 商品上架/下架
    */
    private Boolean onShelve;

    /**
    * 推荐商品id
    */
    private Integer recommendProductId;

    /**
    * 是否重要商品
    */
    private Boolean isImportant;

    /**
    * 编者
    */
    private String editer;

    /**
    * 作者
    */
    private String author;

    /**
    * 是否删除
    */
    private Boolean isDeleted;

    /**
    * 添加时间
    */
    private Date addDt;

    /**
    * 添加用户id
    */
    private Integer addUserId;

    /**
    * 修改时间
    */
    private Date updateDt;

    /**
    * 修改用户id
    */
    private Integer addUpdateId;

    /**
    * 最后同步时间
    */
    private Date lastSyndt;

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

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(Long firstCategory) {
        this.firstCategory = firstCategory;
    }

    public Long getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(Long secondCategory) {
        this.secondCategory = secondCategory;
    }

    public Long getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(Long thirdCategory) {
        this.thirdCategory = thirdCategory;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Integer serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public Integer getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Integer soldCount) {
        this.soldCount = soldCount;
    }

    public Integer getDefaultSoldCount() {
        return defaultSoldCount;
    }

    public void setDefaultSoldCount(Integer defaultSoldCount) {
        this.defaultSoldCount = defaultSoldCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Boolean getOnShelve() {
        return onShelve;
    }

    public void setOnShelve(Boolean onShelve) {
        this.onShelve = onShelve;
    }

    public Integer getRecommendProductId() {
        return recommendProductId;
    }

    public void setRecommendProductId(Integer recommendProductId) {
        this.recommendProductId = recommendProductId;
    }

    public Boolean getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Boolean isImportant) {
        this.isImportant = isImportant;
    }

    public String getEditer() {
        return editer;
    }

    public void setEditer(String editer) {
        this.editer = editer;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Integer getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }

    public Date getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    public Integer getAddUpdateId() {
        return addUpdateId;
    }

    public void setAddUpdateId(Integer addUpdateId) {
        this.addUpdateId = addUpdateId;
    }

    public Date getLastSyndt() {
        return lastSyndt;
    }

    public void setLastSyndt(Date lastSyndt) {
        this.lastSyndt = lastSyndt;
    }
}