/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月27日
 */
package com.crotg.pay.merchant.service.facaed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.facaed.merchant.paypass.po.MerchantWeixinInfo;
import com.crotg.pay.facaed.merchant.paypass.service.MerchantWxInfoFacaed;
import com.crotg.pay.merchant.service.paypass.service.MerchantWxService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月27日
 * @version 1.0
 */
@Service("merchantWxInfoFacaed")
public class MerchantWxInfoFacaedImpl implements MerchantWxInfoFacaed {
  
  @Autowired
  MerchantWxService merchantWxService;
  
  /**
   * @author chengdongdong
   * @date 2017年6月27日
   */
  @Override
  public MerchantWeixinInfo getWeixinPayInfoByMno(String merchantNo) {
    return merchantWxService.getWxInfoByMno(merchantNo);
  }
  
}
