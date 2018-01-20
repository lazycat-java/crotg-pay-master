/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月3日
 */
package com.crotg.pay.service.system.init;

import java.util.Map;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.crotg.pay.common.core.utils.SpringUtils;
import com.crotg.pay.service.system.global.GlobalCache;
import com.crotg.pay.service.system.service.SysProfileService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月3日
 * @version 1.0
 */
public class InitConfig {
  
  /**
   * 初始化redis客户端连接引用
   * */
  public static void initJedisClient() {
    ShardedJedisPool pool = (ShardedJedisPool) SpringUtils.getBean("shardedJedisPool");
    ShardedJedis jedis = pool.getResource();
    GlobalCache.initJedisClient(jedis);
  }
  
  /**
   * 方法功能描述:初始化系统变量
   * 
   * @author chengdongdong
   * @date 2017年7月4日
   * @param
   * @throws
   * @return
   */
  public static void initProfile() {
    SysProfileService profileService = (SysProfileService) SpringUtils.getBean("sysProfileService");
    Map<String, Object> profiles = profileService.getAllProfile();
    if (profiles != null) {
      GlobalCache.initProfile(profiles);
    }
  }
  
}
