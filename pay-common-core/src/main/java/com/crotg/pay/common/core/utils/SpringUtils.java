/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月17日
 */
package com.crotg.pay.common.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年3月17日
 * @version 1.0
 */
@Component
public class SpringUtils implements ApplicationContextAware {
  private static ApplicationContext applicationContext; // Spring应用上下文环境
  
  /*
   * 
   * 实现了ApplicationContextAware 接口，必须实现该方法；
   * 
   * 通过传递applicationContext参数初始化成员变量applicationContext
   */
  
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringUtils.applicationContext = applicationContext;
  }
  
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T getBean(String name) throws BeansException {
    return (T) applicationContext.getBean(name);
  }
}