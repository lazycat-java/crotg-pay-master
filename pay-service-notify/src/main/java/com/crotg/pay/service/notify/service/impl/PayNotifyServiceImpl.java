/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月8日
 */
package com.crotg.pay.service.notify.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.notify.po.PayNotify;
import com.crotg.pay.service.notify.dao.PayNotifyDao;
import com.crotg.pay.service.notify.service.PayNotifyService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月8日
 * @version 1.0
 */
@Service
public class PayNotifyServiceImpl implements PayNotifyService {
  
  @Autowired
  PayNotifyDao payNotifyDao;
  
  /**
   * @author chengdongdong
   * @date 2017年7月8日
   */
  @Override
  public void insertNotify(PayNotify payNotify) {
    payNotifyDao.insertNotify(payNotify);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月10日
   */        
  @Override
  public void updateNotify(PayNotify payNotify) {
    payNotifyDao.updateNotify(payNotify);
  }
  
}
