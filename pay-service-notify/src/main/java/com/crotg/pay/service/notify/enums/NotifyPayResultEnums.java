/*
 *Copyright 2017 Crotg-PAY Group.
 *Date:2017年7月8日
 * */
package com.crotg.pay.service.notify.enums;

/**
 * 类功能描述:
 * @author chengdongdong
 * @date 2017年7月8日
 * @version 1.0
 */
public enum NotifyPayResultEnums {
  
  SUCCESS("支付成功",1),FAIL("支付失败",2);
  
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
  private NotifyPayResultEnums(String desc, int value) {
    this.desc = desc;
    this.value = value;
  }
  
}
