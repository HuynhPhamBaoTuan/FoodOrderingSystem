/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author phuct
 */
public class Refund {

    private int refundId;
    private int orderId;
    private String refundReason;
    private BigDecimal refundAmount;
    private String refundStatus;
    private Date createdAt;
    private String refundReasonImg;
    private int RefundOption;

    public Refund() {
    }

    public Refund(int refundId, int orderId, String refundReason, BigDecimal refundAmount, String refundStatus, Date createdAt) {
        this.refundId = refundId;
        this.orderId = orderId;
        this.refundReason = refundReason;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.createdAt = createdAt;
    }

    public Refund(int orderId, String refundReason, BigDecimal refundAmount, String refundStatus) {
        this.orderId = orderId;
        this.refundReason = refundReason;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
    }

    public Refund(int orderId, String refundReason, BigDecimal refundAmount, String refundStatus, String refundReasonImg, int RefundOption) {
        this.orderId = orderId;
        this.refundReason = refundReason;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.refundReasonImg = refundReasonImg;
        this.RefundOption = RefundOption;
    }

    public Refund(int refundId, int orderId, String refundReason, BigDecimal refundAmount, String refundStatus, String refundReasonImg, Date createdAt, int RefundOption) {
        this.refundId = refundId;
        this.orderId = orderId;
        this.refundReason = refundReason;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
        this.refundReasonImg = refundReasonImg;
        this.createdAt = createdAt;
        this.RefundOption = RefundOption;
    }

    public int getRefundOption() {
        return RefundOption;
    }

    public void setRefundOption(int RefundOption) {
        this.RefundOption = RefundOption;
    }

    public String getRefundReasonImg() {
        return refundReasonImg;
    }

    public void setRefundReasonImg(String refundReasonImg) {
        this.refundReasonImg = refundReasonImg;
    }

    public int getRefundId() {
        return refundId;
    }

    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
