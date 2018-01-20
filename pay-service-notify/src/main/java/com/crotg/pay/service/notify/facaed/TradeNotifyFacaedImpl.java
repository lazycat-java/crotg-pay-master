/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月8日
 */
package com.crotg.pay.service.notify.facaed;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.crotg.pay.common.core.utils.FastJsonUtils;
import com.crotg.pay.facaed.notify.po.PayNotify;
import com.crotg.pay.facaed.notify.service.TradeNotifyFacaed;
import com.crotg.pay.service.notify.service.PayNotifyService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月8日
 * @version 1.0
 */
@Service("tradeNotifyFacaed")
public class TradeNotifyFacaedImpl implements TradeNotifyFacaed {
  
  @Autowired
  JmsTemplate activeMqJmsTemplate;
  
  @Autowired
  PayNotifyService payNotifyService;
  
  Logger logger = LoggerFactory.getLogger(TradeNotifyFacaedImpl.class);
  
  /**
   * @author chengdongdong
   * @date 2017年7月8日
   */
  @Override
  public void insertNotify(PayNotify payNotify) {
    payNotifyService.insertNotify(payNotify);
  }
  
  /**
   * @author chengdongdong
   * @date 2017年7月8日
   */
  @Override
  public void sendNotify(final PayNotify notify) {
    activeMqJmsTemplate.send(new MessageCreator() {
      @Override
      public Message createMessage(Session session) throws JMSException {
        return session.createTextMessage(FastJsonUtils.toJSONString(notify));
      }
    });
  }
  
  
}
