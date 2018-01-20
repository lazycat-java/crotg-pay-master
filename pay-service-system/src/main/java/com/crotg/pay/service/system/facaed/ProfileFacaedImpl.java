/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月4日
 */
package com.crotg.pay.service.system.facaed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedisPool;

import com.crotg.pay.facaed.system.service.ProfileFacaed;
import com.crotg.pay.service.system.service.SysProfileService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月4日
 * @version 1.0
 */
@Service("profileFacaed")
public class ProfileFacaedImpl implements ProfileFacaed {
  
  Logger logger = LoggerFactory.getLogger(ProfileFacaedImpl.class);
  
  @Autowired
  SysProfileService sysProfileService;
  
  @Autowired
  ShardedJedisPool shardedJedisPool;
  
  /**
   * @author chengdongdong
   * @throws Exception
   * @date 2017年7月4日
   */
  @Override
  public String getProfileByCode(String code) {
    return sysProfileService.getProfileByCode(code);
  }
  
}
