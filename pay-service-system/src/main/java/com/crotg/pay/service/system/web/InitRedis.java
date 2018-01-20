/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月17日
 */
package com.crotg.pay.service.system.web;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crotg.pay.service.system.init.InitConfig;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年3月17日
 * @version 1.0
 */
public class InitRedis {
  
  Logger logger = LoggerFactory.getLogger(this.getClass());
  
  public static final String PROFILE_KEY = "profile_key";
  
  /**
   * 方法功能描述:初始化缓存系统
   * 
   * @author chengdongdong
   * @date 2017年7月4日
   * @param
   * @throws
   * @return
   */
  @PostConstruct
  public void initWebContiner() {
    InitConfig.initJedisClient();
    InitConfig.initProfile();
  }
  
}
