package com.crotg.pay.facaed.merchant.paypass.po;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class MerchantWeixinInfo implements Serializable {
  
  /**
	 *
	 **/
  private long id;
  
  /**
   * 商户号
   **/
  private String merchantNo;
  
  /**
   * APP支付APPID
   **/
  private String appAppid;
  
  /**
   * 公众号支付APPID
   **/
  private String jsapiAppid;
  
  /**
   * APP微信分配商户号
   **/
  private String appMchId;
  
  /**
   * 公众号支付商户号
   **/
  private String jsapiMchId;
  
  /**
   * API秘钥
   **/
  private String apiKey;
  
  /**
   * 支付通道状态1正常2关闭
   **/
  private int status;
  
  /**
	 *
	 **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date createDate;
  
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
  
  public void setAppAppid(String appAppid) {
    this.appAppid = appAppid;
  }
  
  public String getAppAppid() {
    return this.appAppid;
  }
  
  public void setJsapiAppid(String jsapiAppid) {
    this.jsapiAppid = jsapiAppid;
  }
  
  public String getJsapiAppid() {
    return this.jsapiAppid;
  }
  
  public void setAppMchId(String appMchId) {
    this.appMchId = appMchId;
  }
  
  public String getAppMchId() {
    return this.appMchId;
  }
  
  public void setJsapiMchId(String jsapiMchId) {
    this.jsapiMchId = jsapiMchId;
  }
  
  public String getJsapiMchId() {
    return this.jsapiMchId;
  }
  
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }
  
  public String getApiKey() {
    return this.apiKey;
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
  
}
