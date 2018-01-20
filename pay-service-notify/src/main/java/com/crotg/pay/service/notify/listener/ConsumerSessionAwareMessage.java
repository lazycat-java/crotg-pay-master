/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月8日
 */
package com.crotg.pay.service.notify.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.Session;

import net.sf.json.JSONObject;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;

import com.crotg.pay.facaed.notify.po.PayNotify;
import com.crotg.pay.common.core.utils.DateUtils;
import com.crotg.pay.common.core.utils.FastJsonUtils;
import com.crotg.pay.common.core.utils.http.HttpUtils;
import com.crotg.pay.facaed.trade.enums.TradeStatusEnums;
import com.crotg.pay.facaed.trade.po.TradeOrder;
import com.crotg.pay.facaed.trade.service.TradeWPUnifiedorderFacaed;
import com.crotg.pay.service.notify.enums.NotifyStatusEnums;
import com.crotg.pay.service.notify.service.PayNotifyService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月8日
 * @version 1.0
 */
public class ConsumerSessionAwareMessage implements SessionAwareMessageListener<Message> {
  
  Logger logger = LoggerFactory.getLogger(ConsumerSessionAwareMessage.class);
  
  @Autowired
  private JmsTemplate activeMqJmsTemplate;
  @Autowired
  private Destination sessionAwareQueue;
  @Autowired
  private PayNotifyService notifyService;
  @Autowired
  private TradeWPUnifiedorderFacaed tradeWPUnifiedorderFacaed;
  
  public synchronized void onMessage(Message message, Session session) {
    try {
      ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
      final String ms = msg.getText();
      logger.info("==>receive message:" + ms);
      try {
        // 处理微信支付回调
        PayNotify notify = FastJsonUtils.toBean(ms, PayNotify.class);
        notify.setCreateDate(new Date());
        TradeOrder tradeOrder = tradeWPUnifiedorderFacaed.selectOne(notify.getMerchantOrderNo(), notify.getMerchantNo());
        notify.setNotifyUrl(tradeOrder.getCallUrl());
        // 添加通知记录
        notifyService.insertNotify(notify);
        // 开始通知
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("merchantOrderNo", notify.getMerchantOrderNo());
        map.put("tradeEndDate", notify.getTradeEndDate());
        if ("SUCCESS".equals(notify.getPayResult())) {
          tradeWPUnifiedorderFacaed.updateTradeOrderStatus(notify.getMerchantOrderNo(), TradeStatusEnums.PAYOK.getValue(), notify.getMerchantNo(), notify.getOrderNo3rd(), notify.getTradeEndDate());
          // 通知商户平台支付成功
          map.put("retrunCode", "SUCCESS");
          try {
            HttpUtils.sendPost(notify.getNotifyUrl(), map);
            notifyService.updateNotify(new PayNotify(new Date(), NotifyStatusEnums.SUCCESS.getValue()));
          } catch (Exception e) {
            logger.error("通知失败,原因:" + e);
            notifyService.updateNotify(new PayNotify(new Date(), NotifyStatusEnums.FAIL.getValue()));
          }
        } else {
          tradeWPUnifiedorderFacaed.updateTradeOrderStatus(notify.getMerchantOrderNo(), TradeStatusEnums.PAYFAIL.getValue(), notify.getMerchantNo(), notify.getOrderNo3rd(), notify.getTradeEndDate());
          map.put("retrunCode", "FAIL");
          try {
            HttpUtils.sendPost(notify.getNotifyUrl(), map);
            notifyService.updateNotify(new PayNotify(new Date(), NotifyStatusEnums.SUCCESS.getValue()));
          } catch (Exception e) {
            logger.error("通知失败,原因:" + e);
            notifyService.updateNotify(new PayNotify(new Date(), NotifyStatusEnums.FAIL.getValue()));
          }
        }
        
      } catch (Exception e) {
        logger.error("==> activemq onmessage error:", e);
      }
    } catch (Exception e) {
      logger.error("==>", e);
    }
  }
  
}
