/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.facaed.trade.service;

import java.util.Date;

import com.crotg.pay.common.core.utils.JsonResult;
import com.crotg.pay.facaed.trade.po.TradeOrder;
import com.crotg.pay.facaed.trade.vo.TradeOrderVo;

/**
 * 类功能描述:微信统一下单接口
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
public interface TradeWPUnifiedorderFacaed {
  
  /**
   * 方法功能描述:微信APP支付接口
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public JsonResult appPay();
  
  /**
   * 方法功能描述:微信公众号支付接口
   * 
   * @author chengdongdong
   * @date 2017年7月5日
   * @param
   * @throws
   * @return
   */
  public JsonResult jsApiPay(TradeOrderVo tradeOrderVo);
  
  /**
   * 
   * 方法功能描述:添加交易订单
   * 
   * @author chengdongdong
   * @date 2017年7月6日
   * @param
   * @throws
   * @return
   */
  public JsonResult insertTradeOrder(TradeOrderVo tradeOrderVo);
  
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
  
  /**
   * 方法功能描述:修改交易订单状态
   * 
   * @author chengdongdong
   * @date 2017年7月10日
   * @param
   * @throws
   * @return
   */
  public void updateTradeOrderStatus(String merchantOrderNo, int status, String merchantNo, String orderNo3Rd, String orderEndTime);
  
}
