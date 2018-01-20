/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.facaed.trade.enums;

/**
 * 类功能描述:微信支付交易类型(1JSAPI2APP)
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public enum TradeTypeEnums {
  
  JSAPI("JSAPI", 1), APP("APP", 2);
  
  private String desc;
  private int value;
  
  public String getDesc() {
    return desc;
  }
  
  public void setDesc(String desc) {
    this.desc = desc;
  }
  
  public int getValue() {
    return value;
  }
  
  public void setValue(int value) {
    this.value = value;
  }
  
  /**
   * 构造方法
   */
  private TradeTypeEnums(String desc, int value) {
    this.desc = desc;
    this.value = value;
  }
  
}
