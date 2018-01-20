/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月30日
 */
package com.crotg.pay.service.system.service;

import java.util.Map;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月30日
 * @version 1.0
 */
public interface SysProfileService {
  
  /**
   * 方法功能描述:查询所有的系统变量放入缓存
   * 
   * @author chengdongdong
   * @date 2017年6月30日
   * @param
   * @throws
   * @return
   */
  public Map<String, Object> getAllProfile();
  
  /**
   * 方法功能描述:根据code查询String类型的系统变量
   * 
   * @author chengdongdong
   * @date 2017年6月29日
   * @param
   * @throws
   * @return
   */
  public String getProfileByCode(String code);
}
