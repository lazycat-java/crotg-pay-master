/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月29日
 */
package com.crotg.pay.merchant.service.paypass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.merchant.paypass.po.MerchantAliInfo;
import com.crotg.pay.merchant.service.paypass.dao.MerchantAliInfoDao;
import com.crotg.pay.merchant.service.paypass.service.MerchantAliService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月29日
 * @version 1.0
 */
@Service
public class MerchantAliServiceImpl implements MerchantAliService {
  
  @Autowired
  private MerchantAliInfoDao merchantAliInfoDao;
  
  /**
   * @author chengdongdong
   * @date 2017年6月29日
   */
  @Override
  public MerchantAliInfo getMerchantAliByMno(String merchantNo) {
    return merchantAliInfoDao.getMerchantAliByMno(merchantNo);
  }
  
}
