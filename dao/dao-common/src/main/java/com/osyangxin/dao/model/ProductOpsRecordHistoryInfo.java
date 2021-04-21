package com.osyangxin.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 商品操作记录表
    */
public class ProductOpsRecordHistoryInfo {
    /**
    * id
    */
    private Long id;

    /**
    * 商品
    */
    private Long productId;

    /**
    * 操作用户 id
    */
    private Integer opsUser;

    /**
    * 操作行为
    */
    private String opsRecord;

    /**
    * 改价前
    */
    private BigDecimal changeBefore;

    /**
    * 改价后
    */
    private BigDecimal changeAfter;

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

    public Integer getOpsUser() {
        return opsUser;
    }

    public void setOpsUser(Integer opsUser) {
        this.opsUser = opsUser;
    }

    public String getOpsRecord() {
        return opsRecord;
    }

    public void setOpsRecord(String opsRecord) {
        this.opsRecord = opsRecord;
    }

    public BigDecimal getChangeBefore() {
        return changeBefore;
    }

    public void setChangeBefore(BigDecimal changeBefore) {
        this.changeBefore = changeBefore;
    }

    public BigDecimal getChangeAfter() {
        return changeAfter;
    }

    public void setChangeAfter(BigDecimal changeAfter) {
        this.changeAfter = changeAfter;
    }

    public Date getAddDt() {
        return addDt;
    }

    public void setAddDt(Date addDt) {
        this.addDt = addDt;
    }
}