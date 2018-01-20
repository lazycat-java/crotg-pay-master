/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.facaed.trade.vo;

import com.crotg.pay.facaed.trade.po.TradeOrder;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public class TradeOrderVo extends TradeOrder {
  
  private static final long serialVersionUID = 409721058483715951L;
  
  private String appid;
  private String mchId;
  private String apiKey;
  
  
  public String getApiKey() {
    return apiKey;
  }
  
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }
  
  
  public String getAppid() {
    return appid;
  }
  
  public void setAppid(String appid) {
    this.appid = appid;
  }
  
  public String getMchId() {
    return mchId;
  }
  
  public void setMchId(String mchId) {
    this.mchId = mchId;
  }
  
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
}
