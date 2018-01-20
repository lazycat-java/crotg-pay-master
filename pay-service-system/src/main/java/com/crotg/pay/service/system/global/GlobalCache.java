/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年7月4日
 */
package com.crotg.pay.service.system.global;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crotg.pay.common.core.redis.SerializeUtils;

import redis.clients.jedis.ShardedJedis;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年7月4日
 * @version 1.0
 */
public class GlobalCache {
  
  static Logger logger = LoggerFactory.getLogger(GlobalCache.class);
  
  /** 缓存客户端 */
  private static ShardedJedis jedis;
  
  /** 系统变量缓存 */
  private static final String PROFILE_KAY = "profile_kay";
  
  /**
   * 初始化缓存客户端 方法功能描述:
   * 
   * @author chengdongdong
   * @date 2017年7月4日
   * @param
   * @throws
   * @return
   */
  public static void initJedisClient(ShardedJedis jedis) {
    GlobalCache.jedis = jedis;
  }
  
  /**
   * 方法功能描述: 初始化系统变量
   * 
   * @author chengdongdong
   * @date 2017年7月4日
   * @param
   * @throws
   * @return
   */
  @SuppressWarnings("unused")
  public static boolean initProfile(Map<String, Object> map) {
    boolean flag = true;
    try {
      if (jedis.exists(SerializeUtils.serialize(PROFILE_KAY))) {
        jedis.del(SerializeUtils.serialize(PROFILE_KAY));
      }
      jedis.set(SerializeUtils.serialize(PROFILE_KAY), SerializeUtils.serialize(map));
      flag = true;
    } catch (Exception e) {
      logger.error("缓存系统变量出错");
      flag = false;
      e.printStackTrace();
    }
    return false;
  }
  
  @SuppressWarnings("unchecked")
  public static String getProfileByCode(String code) {
    String value = null;
    try {
      if (jedis.exists(SerializeUtils.serialize(PROFILE_KAY))) {
        byte[] bs = jedis.get(SerializeUtils.serialize(PROFILE_KAY));
        if (bs != null) {
          Map<String, Object> profiles = (Map<String, Object>) SerializeUtils.unSerialize(bs);
          if (profiles != null) {
            if (profiles.get(code) != null) {
              value = profiles.get(code).toString();
            }
          }
        }
      }
    } catch (Exception e) {
      logger.error("获取变量缓存失败");
      e.printStackTrace();
    }
    return value;
  }
  
}
