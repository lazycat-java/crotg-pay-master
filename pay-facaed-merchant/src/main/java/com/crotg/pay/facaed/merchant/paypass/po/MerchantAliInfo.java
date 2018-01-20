package com.crotg.pay.facaed.merchant.paypass.po;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class MerchantAliInfo implements Serializable {
  
  /**
	 *
	 **/
  private long id;
  
  /**
   * 商户号
   */
  private String merchantNo;
  
  /**
   * 支付宝应用id
   **/
  private String aliApppid;
  
  /**
   * 支付宝公钥
   **/
  private String aliPublicKey;
  
  /**
   * 支付宝应用私钥
   **/
  private String aliPrivateKey;
  
  /**
   * 支付通道状态1正常2关闭
   **/
  private int status;
  
  /**
   * 创建时间
   **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date createDate;
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setAliApppid(String aliApppid) {
    this.aliApppid = aliApppid;
  }
  
  public String getAliApppid() {
    return this.aliApppid;
  }
  
  public void setAliPublicKey(String aliPublicKey) {
    this.aliPublicKey = aliPublicKey;
  }
  
  public String getAliPublicKey() {
    return this.aliPublicKey;
  }
  
  public void setAliPrivateKey(String aliPrivateKey) {
    this.aliPrivateKey = aliPrivateKey;
  }
  
  public String getAliPrivateKey() {
    return this.aliPrivateKey;
  }
  
  public void setStatus(int status) {
    this.status = status;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void setCreateDate(java.util.Date createDate) {
    this.createDate = createDate;
  }
  
  public java.util.Date getCreateDate() {
    return this.createDate;
  }
  
  public String getMerchantNo() {
    return merchantNo;
  }
  
  public void setMerchantNo(String merchantNo) {
    this.merchantNo = merchantNo;
  }
  
}
