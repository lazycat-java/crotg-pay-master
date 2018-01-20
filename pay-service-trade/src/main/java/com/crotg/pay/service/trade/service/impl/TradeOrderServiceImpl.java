/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.service.trade.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.common.core.constants.BizResult;
import com.crotg.pay.common.core.utils.DateUtils;
import com.crotg.pay.common.core.utils.JsonResult;
import com.crotg.pay.common.core.utils.http.HttpResult;
import com.crotg.pay.facaed.trade.constant.TradeParamsConstants;
import com.crotg.pay.facaed.trade.enums.TradeStatusEnums;
import com.crotg.pay.facaed.trade.exception.TradeException;
import com.crotg.pay.facaed.trade.po.TradeOrder;
import com.crotg.pay.facaed.trade.vo.TradeOrderVo;
import com.crotg.pay.service.trade.dao.TradeOrderDao;
import com.crotg.pay.service.trade.remote.RemoteWxUnified;
import com.crotg.pay.service.trade.service.TradeOrderService;
import com.crotg.pay.service.trade.utils.ParseXmlUtils;
import com.crotg.pay.service.trade.utils.Sign;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
@Service
public class TradeOrderServiceImpl implements TradeOrderService {
  
  Logger logger = LoggerFactory.getLogger(TradeOrderService.class);
  
  @Autowired
  TradeOrderDao tradeOrderDao;
  
  @Autowired
  RemoteWxUnified remoteWxUnified;
  
  /**
   * @author chengdongdong
   * @date 2017年7月5日
   */
  @Override
  public JsonResult insertTradeOrder(TradeOrder tradeOrder) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("merchantNo", tradeOrder.getMerchantNo());
    map.put("merchantOrderNo", tradeOrder.getMerchantOrderNo());
    TradeOrder order = tradeOrderDao.selectOne(map);
    JsonResult result = null;
    if (order == null) {
      if (tradeOrder.getExpire() != 0) {
        if (tradeOrder.getExpire() < TradeParamsConstants.TRADE_EXPIRE_TIME) {
          return TradeException.TRADE_ORDER_EXPIRE_ERROR;
        } else {
          Date expireDate = DateUtils.addSecond(tradeOrder.getOrderStartTime(), (int) tradeOrder.getExpire());
          tradeOrder.setOrderExpireTime(expireDate);
        }
      }
      try {
        tradeOrderDao.insertOrder(tradeOrder);
        result = BizResult.BIZ_SUCCESS;
      } catch (Exception e) {
        result = TradeException.TRADE_ORDER_INSERT_ERROR;
        e.printStackTrace();
      }
    }
    
    return result;
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月5日
   */
  @Override
  public void updateStatus(Map<String, Object> map) {
    tradeOrderDao.updateStatus(map);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月5日
   */
  @Override
  public JsonResult jsApiPay(TradeOrderVo tradeOrderVo) {
    long date1 = System.currentTimeMillis();
    JsonResult result = null;
    // long timeStamp = new Date().getTime() / 1000;
    HttpResult httpResult = remoteWxUnified.unified(tradeOrderVo);
    if (httpResult.getStatusCode() == 200) {
      if ("SUCCESS".equals(ParseXmlUtils.doXml(httpResult.getContent(), "return_code"))) {
        // 通信成功
        if ("FAIL".equals(ParseXmlUtils.doXml(httpResult.getContent(), "result_code"))) {
          Map<String, Object> failMap = new HashMap<String, Object>();
          failMap.put("merchantOrderNo", tradeOrderVo.getMerchantOrderNo());
          failMap.put("status", TradeStatusEnums.PAYFAIL.getValue());
          failMap.put("merchantNo", tradeOrderVo.getMerchantNo());
          updateStatus(failMap);
        }
        // 支付成功处理方案
        if ("SUCCESS".equals(ParseXmlUtils.doXml(httpResult.getContent(), "result_code"))) {
          String prepay_id = ParseXmlUtils.doXml(httpResult.getContent(), "prepay_id");
          // 获取prepay_id后，拼接最后请求支付所需要的package
          Map<String, Object> params2 = new HashMap<String, Object>();
          String packages = "";
          packages = "prepay_id=" + prepay_id;
          params2.put("appId", tradeOrderVo.getAppid());
          params2.put("timeStamp", "1395712654");
          params2.put("nonceStr", ParseXmlUtils.doXml(httpResult.getContent(), "nonce_str"));
          params2.put("package", packages);
          params2.put("signType", "MD5");
          // 获取签名
          String finalSign = Sign.getSign(params2, tradeOrderVo.getApiKey());
          Map<String, Object> data = new HashMap<String, Object>();
          data.put("appId", tradeOrderVo.getAppid());
          data.put("timeStamp", "1395712654");
          data.put("nonceStr", ParseXmlUtils.doXml(httpResult.getContent(), "nonce_str"));
          data.put("package", packages);
          data.put("prepayid", prepay_id);
          data.put("signType", "MD5");
          data.put("paySign", finalSign);
          data.put("outTradeNo", tradeOrderVo.getMerchantOrderNo());
          data.put("partnerid", tradeOrderVo.getMchId());
          result = new JsonResult(TradeException.BIZ_SUCCESS, true, "请求成功", data);
        }
      } else {
        logger.error("统一下单接口请求失败:" + ParseXmlUtils.doXml(httpResult.getContent(), "return_msg"));
        return new JsonResult(TradeException.TRADE_PREPAY_ERROR, false, ParseXmlUtils.doXml(httpResult.getContent(), "return_msg"));
      }
    } else {
      return TradeException.REQUEST_ERROR;
    }
    long date2 = System.currentTimeMillis();
    System.out.println("接口时间:" + (date2 - date1));
    return result;
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月7日
   */
  @Override
  public int getOrderStatus(String merchantNo) {
    return tradeOrderDao.getOrderStatus(merchantNo);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月7日
   */
  @Override
  public TradeOrder selectOne(String merchantOrderNo, String merchantNo) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("merchantOrderNo", merchantOrderNo);
    map.put("merchantNo", merchantNo);
    return tradeOrderDao.selectOne(map);
  }
  
}
