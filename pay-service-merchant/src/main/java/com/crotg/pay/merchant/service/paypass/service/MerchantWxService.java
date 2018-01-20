/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月27日
 */
package com.crotg.pay.merchant.service.paypass.service;

import com.crotg.pay.facaed.merchant.paypass.po.MerchantWeixinInfo;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月27日
 * @version 1.0
 */
public interface MerchantWxService {
  
  /**
   * 方法功能描述:根据商户号查询微信支付信息
   * 
   * @author chengdongdong
   * @date 2017年6月27日
   * @param
   * @throws
   * @return
   */
  public MerchantWeixinInfo getWxInfoByMno(String merchantNo);
  
}
