/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月29日
 */
package com.crotg.pay.merchant.service.facaed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.merchant.paypass.po.MerchantAliInfo;
import com.crotg.pay.facaed.merchant.paypass.service.MerchantAliPayInfoFacaed;
import com.crotg.pay.merchant.service.paypass.service.MerchantAliService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月29日
 * @version 1.0
 */
@Service("merchantAliPayInfoFacaed")
public class MerchantAliInfoFacaedImpl implements MerchantAliPayInfoFacaed {
  
  @Autowired
  private MerchantAliService merchantAliService;
  
  /**
   * @author chengdongdong
   * @date 2017年6月29日
   */
  @Override
  public MerchantAliInfo getAliPayInfoByMno(String merchantNo) {
    return merchantAliService.getMerchantAliByMno(merchantNo);
  }
  
}
