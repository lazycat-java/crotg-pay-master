/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月29日
 */
package com.crotg.pay.merchant.service.paypass.dao;

import com.crotg.pay.facaed.merchant.paypass.po.MerchantAliInfo;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月29日
 * @version 1.0
 */
public interface MerchantAliInfoDao {
  
  /**
   * 方法功能描述:根据商户号查询商户支付宝支付信息
   * 
   * @author chengdongdong
   * @date 2017年6月29日
   * @param
   * @throws
   * @return
   */
  public MerchantAliInfo getMerchantAliByMno(String merchantNo);
  
}
