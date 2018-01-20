/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月8日
 */
package com.crotg.pay.service.notify.service;

import com.crotg.pay.facaed.notify.po.PayNotify;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月8日
 * @version 1.0
 */
public interface PayNotifyService {
  
  /**
   * 方法功能描述:添加通知记录
   * 
   * @author chengdongdong
   * @date 2017年7月8日
   * @param
   * @throws
   * @return
   */
  public void insertNotify(PayNotify payNotify);
  
  /**
   * 方法功能描述:通知商户后台以后修改通知记录状态
   * 
   * @author chengdongdong
   * @date 2017年7月10日
   * @param
   * @throws
   * @return
   */
  public void updateNotify(PayNotify payNotify);
  
}
