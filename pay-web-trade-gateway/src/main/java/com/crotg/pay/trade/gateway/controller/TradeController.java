/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月28日
 */
package com.crotg.pay.trade.gateway.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crotg.pay.common.core.utils.DateUtils;
import com.crotg.pay.common.core.utils.FastJsonUtils;
import com.crotg.pay.common.core.utils.JsonResult;
import com.crotg.pay.common.core.utils.StringUtils;
import com.crotg.pay.facaed.merchant.paypass.po.MerchantWeixinInfo;
import com.crotg.pay.facaed.merchant.paypass.service.MerchantWxInfoFacaed;
import com.crotg.pay.facaed.system.service.ProfileFacaed;
import com.crotg.pay.facaed.trade.constant.TradeParamsConstants;
import com.crotg.pay.facaed.trade.enums.TradePassEnums;
import com.crotg.pay.facaed.trade.enums.TradeStatusEnums;
import com.crotg.pay.facaed.trade.exception.TradeException;
import com.crotg.pay.facaed.trade.po.TradeOrder;
import com.crotg.pay.facaed.trade.service.TradeWPUnifiedorderFacaed;
import com.crotg.pay.facaed.trade.vo.TradeOrderVo;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月28日
 * @version 1.0
 */
@RestController
@RequestMapping("trade")
public class TradeController {
  
  Logger logger = LoggerFactory.getLogger(TradeController.class);
  
  @Autowired
  ProfileFacaed profileFacaed;
  
  @Autowired
  MerchantWxInfoFacaed merchantWxInfoFacaed;
  
  @Autowired
  TradeWPUnifiedorderFacaed tradeWPUnifiedorderFacaed;
  
  /**
   * 方法功能描述:支付接口
   * 
   * @author chengdongdong
   * @date 2017年7月6日
   * @param
   * @throws
   * @return
   */
  @RequestMapping(value = "pay", method = RequestMethod.POST)
  public JsonResult getInfo(HttpServletRequest request, TradeOrderVo tradeOrderVo) {
    logger.info(" ==> trade gateway to pay , params :" + FastJsonUtils.toJSONString(tradeOrderVo));
    // 校验参数是否有效
    String merchantNo = request.getHeader("merchantNo");
    // 初始化支付订单开始时间
    tradeOrderVo.setOrderStartTime(new Date());
    if (StringUtils.isBlank(merchantNo)) {
      return TradeException.TRADE_MERCHANT_NO_ERROR;
    }
    MerchantWeixinInfo weixinInfo = merchantWxInfoFacaed.getWeixinPayInfoByMno(merchantNo);
    if (weixinInfo == null) {
      return TradeException.TRADE_MERCHANT_NO_NOTEXIST;
    }
    if (StringUtils.isBlank(tradeOrderVo.getTradeDesc())) {
      return TradeException.TRADE_DESC_ERROR;
    }
    if (StringUtils.isBlank(tradeOrderVo.getMerchantOrderNo())) {
      return TradeException.TRADE_MERCHANT_ORDERNO_ERROR;
    }
    if (tradeOrderVo.getTotalFee() == 0) {
      return TradeException.TRADE_TOTAL_FEE_ERROR;
    }
    if (StringUtils.isBlank(tradeOrderVo.getSpbillCreateIp())) {
      return TradeException.TRADE_CREATE_IP_ERROR;
    }
    if (tradeOrderVo.getPayPass() == 0) {
      return TradeException.TRADE_PAY_PASS_ERROR;
    }
    if (tradeOrderVo.getPayPass() == TradePassEnums.WEIXIN.getValue()) {
      if (StringUtils.isBlank(tradeOrderVo.getOpenid())) {
        return TradeException.TRADE_PAY_OPENID_ERROR;
      }
    }
    if (StringUtils.isBlank(tradeOrderVo.getCallUrl())) {
      return TradeException.TRADE_CALL_URL_ERROR;
    }
    if (0 < tradeOrderVo.getExpire() && tradeOrderVo.getExpire() < TradeParamsConstants.TRADE_EXPIRE_TIME) {
      return TradeException.TRADE_EXPIRE_ERROR;
    } else {
      tradeOrderVo.setExpire(tradeOrderVo.getExpire());
      tradeOrderVo.setOrderExpireTime(DateUtils.addSecond(tradeOrderVo.getOrderStartTime(), (int) tradeOrderVo.getExpire()));
    }
    // 调用商户服务,完善商户支付信息
    tradeOrderVo.setMerchantNo(merchantNo);
    tradeOrderVo.setAppid(weixinInfo.getJsapiAppid());
    tradeOrderVo.setMchId(weixinInfo.getJsapiMchId());
    tradeOrderVo.setApiKey(weixinInfo.getApiKey());
    // 添加支付订单
    tradeWPUnifiedorderFacaed.insertTradeOrder(tradeOrderVo);
    // 校验订单参数有效性
    TradeOrder tradeOrder = tradeWPUnifiedorderFacaed.selectOne(tradeOrderVo.getMerchantOrderNo(),tradeOrderVo.getMerchantNo());
    // 校验订单是否超时
    if (tradeOrder.getExpire() != 0) {
      if (tradeOrder.getOrderExpireTime().before(new Date())) {
        return TradeException.TRADE_ORDER_EXPIRED;
      }
    }
    // 校验订单状态
    if (tradeOrder.getStatus() != TradeStatusEnums.WAITPAY.getValue() && tradeOrder.getStatus() != TradeStatusEnums.PAYFAIL.getValue()) {
      System.out.println("订单状态:" + tradeOrder.getStatus());
      return TradeException.TRADE_ORDER_STATUS_ERROR;
    }
    // 返回结果
    JsonResult result = null;
    if (tradeOrderVo.getPayPass() == TradePassEnums.WEIXIN.getValue()) {
      try {
        result = tradeWPUnifiedorderFacaed.jsApiPay(tradeOrderVo);
      } catch (Exception e) {
        result = TradeException.TRADE_PAY_ERROR;
        e.printStackTrace();
      }
    }
    return result;
  }
}
