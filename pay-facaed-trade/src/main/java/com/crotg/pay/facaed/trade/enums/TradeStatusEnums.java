/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.facaed.trade.enums;

/**
 * 类功能描述:1待支付,2支付成功,3支付失败,4已退款,5超时未支付,6提现
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public enum TradeStatusEnums {
  
  WAITPAY("待支付", 1), PAYOK("支付成功", 2), PAYFAIL("支付失败", 3), REFUND("已退款", 4), OUTTIME("超时未支付", 5), WITHDRAW("提现", 6);
  
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
  private TradeStatusEnums(String desc, int value) {
    this.desc = desc;
    this.value = value;
  }
  
}
