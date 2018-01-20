/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月7日
 */
package com.crotg.pay.trade.gateway.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crotg.pay.common.core.utils.DateUtils;
import com.crotg.pay.common.core.utils.StringUtils;
import com.crotg.pay.facaed.notify.constant.NotifyTypeEnums;
import com.crotg.pay.facaed.notify.po.PayNotify;
import com.crotg.pay.facaed.notify.service.TradeNotifyFacaed;
import com.crotg.pay.trade.gateway.utils.ParseXmlUtils;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月7日
 * @version 1.0
 */
@RestController
@RequestMapping("/notify")
public class TradeNotifyController {
  
  Logger logger = LoggerFactory.getLogger(TradeNotifyController.class);
  
  @Autowired
  TradeNotifyFacaed tradeNotifyFacaed;
  
  /**
   * 方法功能描述:微信成功支付后回调地址
   * 
   * @author chengdongdong
   * @date 2017年7月7日
   * @param
   * @throws
   * @return
   */
  @PostMapping("/wxnotify")
  public void wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
    logger.debug(" ==> weixin pay notify producer");
    // 定义编码
    try {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      /** 读取接收到的xml消息 */
      StringBuffer sb = new StringBuffer();
      InputStream is = request.getInputStream();
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");
      BufferedReader br = new BufferedReader(isr);
      String s = "";
      while ((s = br.readLine()) != null) {
        sb.append(s);
      }
      String xml = sb.toString(); // 接收到微信端发送过来的xml数据
      logger.debug(" == > weixinpay callback xml:" + xml);
      if (StringUtils.isNotBlank(xml)) {
        String merchantNo = ParseXmlUtils.doXml(xml, "attach");
        String merchantOrderNo = ParseXmlUtils.doXml(xml, "out_trade_no");
        String payResult = ParseXmlUtils.doXml(xml, "result_code");
        String tradeEndDate = ParseXmlUtils.doXml(xml, "time_end");
        String orderNo3Rd = ParseXmlUtils.doXml(xml, "transaction_id");
        PayNotify notify = new PayNotify();
        notify.setMerchantNo(merchantNo);
        notify.setMerchantOrderNo(merchantOrderNo);
        notify.setPayResult(payResult);
        notify.setTradeEndDate(tradeEndDate);
        notify.setOrderNo3rd(orderNo3Rd);
        notify.setNotifyType(NotifyTypeEnums.WEIXIN.getValue());
        tradeNotifyFacaed.sendNotify(notify);
      }
      response.getWriter().write("SUCCESS");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
}
