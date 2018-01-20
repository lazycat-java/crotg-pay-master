/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.service.trade.service;

import java.util.Map;

import com.crotg.pay.common.core.utils.JsonResult;
import com.crotg.pay.facaed.trade.po.TradeOrder;
import com.crotg.pay.facaed.trade.vo.TradeOrderVo;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public interface TradeOrderService {
  
  /**
   * 方法功能描述:添加订单
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public JsonResult insertTradeOrder(TradeOrder tradeOrder);
  
  /**
   * 方法功能描述:修改订单状态
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public void updateStatus(Map<String, Object> map);
  
  /**
   * 
   * 方法功能描述:APP支付
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public JsonResult jsApiPay(TradeOrderVo tradeOrderVo);
  
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
  public TradeOrder selectOne(String merchantOrderNo, String merchantNo);
  
}
