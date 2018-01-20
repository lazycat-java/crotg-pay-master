package com.crotg.pay.facaed.notify.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class PayNotify implements Serializable {
  
  /**
   * 构造方法
   */
  public PayNotify() {
    super();
  }
  
  /**
	 *
	 **/
  private long id;
  
  /**
   * 商户号
   **/
  private String merchantNo;
  
  /**
   * 商户订单号
   **/
  private String merchantOrderNo;
  
  /**
   * 通知商户地址
   **/
  private String notifyUrl;
  
  /**
   * 通知类型1微信2支付宝
   **/
  private int notifyType;
  
  /**
   * 创建时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date createDate;
  
  /**
   * 通知时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date notifyDate;
  
  /**
   * 通知次数
   **/
  private long notifyTimes;
  
  /**
   * 1待通知2成功3失败
   **/
  private int status;
  
  /**
   * 1支付成功2支付失败
   */
  private String payResult;
  
  private String errorMsg;
  
  /************************************************************************************************************/
  /** -- VO -- */
  /************************************************************************************************************/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String tradeEndDate;
  private String orderNo3rd;
  
  public String getTradeEndDate() {
    return tradeEndDate;
  }
  
  public void setTradeEndDate(String tradeEndDate) {
    this.tradeEndDate = tradeEndDate;
  }
  
  public String getOrderNo3rd() {
    return orderNo3rd;
  }
  
  public void setOrderNo3rd(String orderNo3rd) {
    this.orderNo3rd = orderNo3rd;
  }
  
  public String getErrorMsg() {
    return errorMsg;
  }
  
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  
  public String getPayResult() {
    return payResult;
  }
  
  public void setPayResult(String payResult) {
    this.payResult = payResult;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setMerchantNo(String merchantNo) {
    this.merchantNo = merchantNo;
  }
  
  public String getMerchantNo() {
    return this.merchantNo;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo) {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo() {
    return this.merchantOrderNo;
  }
  
  public void setNotifyUrl(String notifyUrl) {
    this.notifyUrl = notifyUrl;
  }
  
  public String getNotifyUrl() {
    return this.notifyUrl;
  }
  
  public void setNotifyType(int notifyType) {
    this.notifyType = notifyType;
  }
  
  public int getNotifyType() {
    return this.notifyType;
  }
  
  public void setCreateDate(java.util.Date createDate) {
    this.createDate = createDate;
  }
  
  public java.util.Date getCreateDate() {
    return this.createDate;
  }
  
  public void setNotifyDate(java.util.Date notifyDate) {
    this.notifyDate = notifyDate;
  }
  
  public java.util.Date getNotifyDate() {
    return this.notifyDate;
  }
  
  public void setNotifyTimes(long notifyTimes) {
    this.notifyTimes = notifyTimes;
  }
  
  public long getNotifyTimes() {
    return this.notifyTimes;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  /**
   * 构造方法
   */
  public PayNotify(String merchantNo, String merchantOrderNo, String payResult, int notifyType) {
    super();
    this.merchantNo = merchantNo;
    this.merchantOrderNo = merchantOrderNo;
    this.payResult = payResult;
    this.notifyType = notifyType;
  }
  
  /**
   * 构造方法
   */
  public PayNotify(Date notifyDate, int status) {
    super();
    this.notifyDate = notifyDate;
    this.status = status;
  }
  
}
