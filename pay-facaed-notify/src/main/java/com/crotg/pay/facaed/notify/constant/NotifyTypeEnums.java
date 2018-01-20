/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月14日
 */
package com.crotg.pay.facaed.notify.constant;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月14日
 * @version 1.0
 */
public enum NotifyTypeEnums {
  
  WEIXIN("微信", 1), ALIPAY("支付宝", 2);
  
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
  private NotifyTypeEnums(String desc, int value) {
    this.desc = desc;
    this.value = value;
  }
  
}
