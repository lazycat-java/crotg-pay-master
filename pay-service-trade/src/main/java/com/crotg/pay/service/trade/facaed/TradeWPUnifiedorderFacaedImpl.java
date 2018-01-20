/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.service.trade.facaed;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.common.core.utils.DateUtils;
import com.crotg.pay.common.core.utils.JsonResult;
import com.crotg.pay.facaed.trade.po.TradeOrder;
import com.crotg.pay.facaed.trade.service.TradeWPUnifiedorderFacaed;
import com.crotg.pay.facaed.trade.vo.TradeOrderVo;
import com.crotg.pay.service.trade.service.TradeOrderService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
@Service("tradeWPUnifiedorderFacaed")
public class TradeWPUnifiedorderFacaedImpl implements TradeWPUnifiedorderFacaed {
  
  @Autowired
  TradeOrderService tradeOrderService;
  
  /**
   * @author chengdongdong
   * @date 2017年7月5日
   */
  @Override
  public JsonResult appPay() {
    // TODO Auto-generated method stub
    return null;
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月5日
   */
  @Override
  public JsonResult jsApiPay(TradeOrderVo tradeOrderVo) {
    return tradeOrderService.jsApiPay(tradeOrderVo);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月6日
   */
  @Override
  public JsonResult insertTradeOrder(TradeOrderVo tradeOrderVo) {
    return tradeOrderService.insertTradeOrder(tradeOrderVo);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月7日
   */
  @Override
  public int getOrderStatus(String merchantNo) {
    return tradeOrderService.getOrderStatus(merchantNo);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月7日
   */
  @Override
  public TradeOrder selectOne(String merchantOrderNo, String merchantNo) {
    return tradeOrderService.selectOne(merchantOrderNo, merchantNo);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月10日
   */
  @Override
  public void updateTradeOrderStatus(String merchantOrderNo, int status, String merchantNo, String orderNo3Rd, String orderEndTime) {
    System.out.println("订单结束时间:"+orderEndTime);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("merchantOrderNo", merchantOrderNo);
    map.put("status", status);
    map.put("merchantNo", merchantNo);
    map.put("orderNo3rd", orderNo3Rd);
    map.put("orderEndTime", orderEndTime);
    tradeOrderService.updateStatus(map);
  }
}
