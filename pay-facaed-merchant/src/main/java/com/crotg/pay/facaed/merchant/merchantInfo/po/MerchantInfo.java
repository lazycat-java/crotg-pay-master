package com.crotg.pay.facaed.merchant.merchantInfo.po;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class MerchantInfo implements Serializable {
  
  /**sss
	 *
	 **/
  private long id;
  
  /**
   * 商户名称
   **/
  private String merchantName;
  
  /**
   * 商户号(trade+日期)
   **/
  private String merchantNo;
  
  /**
   * 商户状态1:正常2:冻结
   **/
  private int status;
  
  /**
   * 冻结原因
   **/
  private String frozenReason;
  
  /**
   * 1开通1未开通
   **/
  private int isOpenWxPay;
  
  /**
	 *
	 **/
  private int isOpenAliPay;
  
  /**
   * 创建时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date creteDate;
  
  /**
   * 支付密码,打款需要校验
   **/
  private String payPassword;
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setMerchantName(String merchantName) {
    this.merchantName = merchantName;
  }
  
  public String getMerchantName() {
    return this.merchantName;
  }
  
  public void setMerchantNo(String merchantNo) {
    this.merchantNo = merchantNo;
  }
  
  public String getMerchantNo() {
    return this.merchantNo;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void setFrozenReason(String frozenReason) {
    this.frozenReason = frozenReason;
  }
  
  public String getFrozenReason() {
    return this.frozenReason;
  }
  
  public void setIsOpenWxPay(int isOpenWxPay) {
    this.isOpenWxPay = isOpenWxPay;
  }
  
  public int getIsOpenWxPay() {
    return this.isOpenWxPay;
  }
  
  public void setIsOpenAliPay(int isOpenAliPay) {
    this.isOpenAliPay = isOpenAliPay;
  }
  
  public int getIsOpenAliPay() {
    return this.isOpenAliPay;
  }
  
  public void setCreteDate(java.util.Date creteDate) {
    this.creteDate = creteDate;
  }
  
  public java.util.Date getCreteDate() {
    return this.creteDate;
  }
  
  public void setPayPassword(String payPassword) {
    this.payPassword = payPassword;
  }
  
  public String getPayPassword() {
    return this.payPassword;
  }
  
}
