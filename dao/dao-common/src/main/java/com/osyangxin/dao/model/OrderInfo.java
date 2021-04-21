package com.osyangxin.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类描述
 * @time 2021/4/21  21:30
 * @author yangxin
 */
/**
    * 订单表
    */
public class OrderInfo {
    private Long id;

    /**
    *  下单用户 id
    */
    private Long orderUserId;

    /**
    * 订单来源
    */
    private String source;

    /**
    * 渠道订单号（业务线订单号）
    */
    private String orderNo;

    /**
    * 子订单号状态 0 初始状态   10 待支付     20 已支付   40 已完成     50已退款        51部分退款     70 订单取消
    */
    private Short status;

    /**
    * 商品总金额（纯商品价格合计）
    */
    private BigDecimal productPrice;

    /**
    * 优惠总金额
    */
    private BigDecimal discountPrice;

    /**
    * 退款金额
    */
    private BigDecimal refundPrice;

    /**
    * 订单运费
    */
    private BigDecimal postage;

    /**
    * 订单应付金额（商品总金额+订单运费-优惠总金额）
    */
    private BigDecimal shouldPayPrice;

    /**
    * 订单内商品总数量
    */
    private Integer productCount;

    /**
    * 订单实付金额
    */
    private BigDecimal paymentPrice;

    /**
    * 支付方式
    */
    private String paymentMethod;

    /**
    * 支付时间
    */
    private Date paymentTime;

    /**
    * 支付单号
    */
    private String paymentNo;

    /**
    * 订单退款时间
    */
    private Date orderRefundDt;

    /**
    * 是否删除
    */
    private Boolean isDeleted;

    /**
    * 订单取消的原因
    */
    private String cancelReason;

    /**
    * 订单完成时间
    */
    private Date finishDt;

    /**
    * 添加时间
    */
    private Date addDt;

    /**
    * 修改时间
    */
    private Date updateDt;

    /**
    * 供应商订单状态
    */
    private String supplierOrderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Long orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public BigDecimal getShouldPayPrice() {
        return shouldPayPrice;
    }

    public void setShouldPayPrice(BigDecimal shouldPayPrice) {
        this.shouldPayPrice = shouldPayPrice;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(BigDecimal paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Date getOrderRefundDt() {
        return orderRefundDt;
    }

    public void setOrderRefundDt(Date orderRefundDt) {
        this.orderRefundDt = orderRefundDt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Date getFinishDt() {
        return finishDt;
    }

    public void setFinishDt(Date finishDt) {
        this.finishDt = finishDt;
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

    public String getSupplierOrderStatus() {
        return supplierOrderStatus;
    }

    public void setSupplierOrderStatus(String supplierOrderStatus) {
        this.supplierOrderStatus = supplierOrderStatus;
    }
}