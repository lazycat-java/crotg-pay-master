/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月27日
 */
package com.crotg.pay.merchant.service.paypass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.merchant.paypass.po.MerchantWeixinInfo;
import com.crotg.pay.merchant.service.merchant.dao.MerchantDao;
import com.crotg.pay.merchant.service.paypass.dao.MerchantWxInfoDao;
import com.crotg.pay.merchant.service.paypass.service.MerchantWxService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月27日
 * @version 1.0
 */
@Service
public class MerchantWxServiceImpl implements MerchantWxService {
  
  @Autowired
  MerchantWxInfoDao merchantWxInfoDao;
  
  @Autowired
  MerchantDao merchantDao;
  
  /**
   * 方法功能描述:根据商户号查询微信支付信息
   * 
   * @author chengdongdong
   * @date 2017年6月27日
   * @param
   * @throws
   * @return
   */
  public MerchantWeixinInfo getWxInfoByMno(String merchantNo) {
    return merchantWxInfoDao.getWxInfoByMno(merchantNo);
  }
  
}
