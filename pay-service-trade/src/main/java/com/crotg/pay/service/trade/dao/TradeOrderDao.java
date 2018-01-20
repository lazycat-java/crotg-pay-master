/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.service.trade.dao;

import java.util.Map;

import com.crotg.pay.facaed.trade.po.TradeOrder;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public interface TradeOrderDao {
  
  /**
   * 方法功能描述:添加交易订单
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public void insertOrder(TradeOrder tradeOrder);
  
  /**
   * 
   * 方法功能描述:selectOne
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public TradeOrder selectOne(Map<String,Object> map);
  
  /**
   * 方法功能描述:修改订单状态码
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public void updateStatus(Map<String, Object> params);
  
  /**
   * 方法功能描述:根据商户号查询订单状态
   * 
   * @author chengdongdong
   * @date 2017年7月7日
   * @param
   * @throws
   * @return
   */
  public int getOrderStatus(String merchantNo);
  
}
