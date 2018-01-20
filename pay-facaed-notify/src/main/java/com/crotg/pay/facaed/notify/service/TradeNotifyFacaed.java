/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月7日
 */
package com.crotg.pay.facaed.notify.service;

import com.crotg.pay.facaed.notify.po.PayNotify;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月7日
 * @version 1.0
 */
public interface TradeNotifyFacaed {
  
  /**
   * 
   * 方法功能描述:添加通知
   * 
   * @author chengdongdong
   * @date 2017年7月7日
   * @param
   * @throws
   * @return
   */
  public void insertNotify(PayNotify payNotify);
  
  /**
   * 添加消息至消息队列
   * 方法功能描述:
   * @author chengdongdong
   * @date 2017年7月8日
   * @param 
   * @throws
   * @return
   */
  public void sendNotify(PayNotify notify);
  
}
