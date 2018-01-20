package com.crotg.pay.facaed.trade.po;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class TradeOrder implements Serializable {
  
  /**
	 *
	 **/
  private long id;
  
  /**
   * 商户号
   **/
  private String merchantNo;
  
  /**
   * 交易描述
   **/
  private String tradeDesc;
  
  /**
   * 商户系统订单号
   **/
  private String merchantOrderNo;
  
  /**
   * 支付金额(分为单位)
   **/
  private long totalFee;
  
  /**
   * 终端IP
   **/
  private String spbillCreateIp;
  
  /**
   * 1微信2支付宝
   **/
  private int payPass;
  
  /**
   * 订单开始时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date orderStartTime;
  
  /**
   * 超时时间(S为单位最低五分钟)
   **/
  private long expire;
  
  /**
   * 订单超时时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date orderExpireTime;
  
  /**
   * 微信支付交易类型(1JSAPI2APP)
   **/
  private int tradeType;
  
  /**
   * JSAPI必须参数
   **/
  private String openid;
  
  /**
	 *
	 **/
  private String orderNo3rd;
  
  /**
   * 1待支付,2支付成功,3支付失败,4已退款,5超时未支付,6提现
   **/
  private int status;
  
  /**
   * 订单结束时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date orderEndTime;
  
  /**
   * 支付成功通知商户回调地址
   **/
  private String callUrl;
  
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
  
  public void setTradeDesc(String tradeDesc) {
    this.tradeDesc = tradeDesc;
  }
  
  public String getTradeDesc() {
    return this.tradeDesc;
  }
  
  public void setMerchantOrderNo(String merchantOrderNo) {
    this.merchantOrderNo = merchantOrderNo;
  }
  
  public String getMerchantOrderNo() {
    return this.merchantOrderNo;
  }
  
  public void setTotalFee(long totalFee) {
    this.totalFee = totalFee;
  }
  
  public long getTotalFee() {
    return this.totalFee;
  }
  
  public void setSpbillCreateIp(String spbillCreateIp) {
    this.spbillCreateIp = spbillCreateIp;
  }
  
  public String getSpbillCreateIp() {
    return this.spbillCreateIp;
  }
  
  public void setPayPass(int payPass) {
    this.payPass = payPass;
  }
  
  public int getPayPass() {
    return this.payPass;
  }
  
  public void setOrderStartTime(java.util.Date orderStartTime) {
    this.orderStartTime = orderStartTime;
  }
  
  public java.util.Date getOrderStartTime() {
    return this.orderStartTime;
  }
  
  public void setExpire(long expire) {
    this.expire = expire;
  }
  
  public long getExpire() {
    return this.expire;
  }
  
  public void setOrderExpireTime(java.util.Date orderExpireTime) {
    this.orderExpireTime = orderExpireTime;
  }
  
  public java.util.Date getOrderExpireTime() {
    return this.orderExpireTime;
  }
  
  public void setTradeType(int tradeType) {
    this.tradeType = tradeType;
  }
  
  public int getTradeType() {
    return this.tradeType;
  }
  
  public void setOpenid(String openid) {
    this.openid = openid;
  }
  
  public String getOpenid() {
    return this.openid;
  }
  
  public void setOrderNo3rd(String orderNo3rd) {
    this.orderNo3rd = orderNo3rd;
  }
  
  public String getOrderNo3rd() {
    return this.orderNo3rd;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void setOrderEndTime(java.util.Date orderEndTime) {
    this.orderEndTime = orderEndTime;
  }
  
  public java.util.Date getOrderEndTime() {
    return this.orderEndTime;
  }
  
  public void setCallUrl(String callUrl) {
    this.callUrl = callUrl;
  }
  
  public String getCallUrl() {
    return this.callUrl;
  }
  
}
