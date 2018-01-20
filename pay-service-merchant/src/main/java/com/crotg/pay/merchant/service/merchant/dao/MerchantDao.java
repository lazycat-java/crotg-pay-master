/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月29日
 */
package com.crotg.pay.merchant.service.merchant.dao;

import com.crotg.pay.facaed.merchant.merchantInfo.po.MerchantInfo;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月29日
 * @version 1.0
 */
public interface MerchantDao {
  
  /**
   * 方法功能描述:根据商户号查询商户信息
   * 
   * @author chengdongdong
   * @date 2017年6月29日
   * @param
   * @throws
   * @return
   */
  public MerchantInfo getMerchantByMno(String merchantNo);
  
}
