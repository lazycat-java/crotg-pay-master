/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.facaed.trade.enums;

/**
 * 类功能描述:支付通道枚举
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public enum TradePassEnums {
  
  WEIXIN("微信支付", 1), ALIPAY("支付宝", 2);
  
  /** 描述 */
  private String desc;
  /** 枚举值 */
  private int value;
  
  /**
   * 构造方法
   */
  private TradePassEnums(String desc, int value) {
    this.desc = desc;
    this.value = value;
  }
  
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
  
}
