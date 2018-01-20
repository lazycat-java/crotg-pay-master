/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月5日
 */
package com.crotg.pay.service.trade.remote;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.common.core.security.Encodes;
import com.crotg.pay.common.core.utils.DateUtils;
import com.crotg.pay.common.core.utils.FastJsonUtils;
import com.crotg.pay.common.core.utils.MapUtils;
import com.crotg.pay.common.core.utils.http.HttpResult;
import com.crotg.pay.common.core.utils.http.HttpUtils;
import com.crotg.pay.facaed.system.constant.ProfileConstants;
import com.crotg.pay.facaed.system.service.ProfileFacaed;
import com.crotg.pay.facaed.trade.enums.TradeTypeEnums;
import com.crotg.pay.facaed.trade.po.UnifiedEntity;
import com.crotg.pay.facaed.trade.vo.TradeOrderVo;
import com.crotg.pay.service.trade.utils.Map2XmlUtils;
import com.crotg.pay.service.trade.utils.Sign;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月5日
 * @version 1.0
 */
@Service
public class RemoteWxUnified {
  
  Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Autowired
  ProfileFacaed profileFacaed;
  
  public HttpResult unified(TradeOrderVo tradeOrderVo) {
    logger.debug("微信统一下单接口");
    HttpResult result = null;
    // 获取统一下单远程地址
    String unifiedUrl = profileFacaed.getProfileByCode(ProfileConstants.UNIFIED_URL);
    String notifyUrl = profileFacaed.getProfileByCode(ProfileConstants.WXNOTIY_URL);
    logger.debug("回调地址:"+notifyUrl);
    logger.debug(unifiedUrl);
    // 生成随机串,保证签名不可预测
    String nonceStr = Encodes.nonceString(32);
    // 统一下单实体类
    UnifiedEntity unifiedEntity = null;
    System.out.println(FastJsonUtils.toJSONString(tradeOrderVo));
    if (tradeOrderVo.getTradeType() == TradeTypeEnums.JSAPI.getValue()) {
      unifiedEntity = new UnifiedEntity(tradeOrderVo.getAppid(), tradeOrderVo.getMerchantNo(), DateUtils.LONGDATEFORMAT.format(tradeOrderVo.getOrderStartTime()),
          tradeOrderVo.getMchId(), tradeOrderVo.getTradeDesc(), tradeOrderVo.getTotalFee(), tradeOrderVo.getMerchantOrderNo(), tradeOrderVo.getSpbillCreateIp(), TradeTypeEnums.JSAPI.getDesc(),
          tradeOrderVo.getOpenid(), nonceStr, notifyUrl);
    }
    if (tradeOrderVo.getTradeType() == TradeTypeEnums.APP.getValue()) {
      unifiedEntity = new UnifiedEntity(tradeOrderVo.getAppid(), tradeOrderVo.getMerchantNo(), DateUtils.LONGDATEFORMAT.format(tradeOrderVo.getOrderStartTime()),
          tradeOrderVo.getMchId(), tradeOrderVo.getTradeDesc(), tradeOrderVo.getTotalFee(), tradeOrderVo.getMerchantOrderNo(), tradeOrderVo.getSpbillCreateIp(), nonceStr, notifyUrl);
    }
    // 签名
    try {
      String sign = Sign.getSign(MapUtils.beanToMap(unifiedEntity), tradeOrderVo.getApiKey());
      unifiedEntity.setSign(sign);
      result = HttpUtils.sendPost(unifiedUrl, Map2XmlUtils.mapToXMLTest2(MapUtils.beanToMap(unifiedEntity)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  
}
