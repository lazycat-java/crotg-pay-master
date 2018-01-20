/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月8日
 */
package com.crotg.pay.service.notify.enums;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月8日
 * @version 1.0
 */
public enum NotifyStatusEnums {
  
  WAITNOTIFY("待通知", 1), SUCCESS("通知成功", 2), FAIL("通知失败", 3);
  
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
  private NotifyStatusEnums(String desc, int value) {
    this.desc = desc;
    this.value = value;
  }
  
}
