/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月14日
 */
package com.crotg.pay.service.system.dao;

import java.util.List;

import com.crotg.pay.facaed.system.po.SysProfile;


/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年3月14日
 * @version 1.0
 */
public interface SysProfileDao {
  
  /**
   * 查询出所有系统变量放入缓存 功能描述:
   * 
   * @author chengdongdong
   * @date 2017年3月20日
   * @param
   * @throws
   * @return
   */
  public List<SysProfile> getAllProfileCache();
  
  /**
   * 根据code查询vlaue
   * 方法功能描述:
   * @author chengdongdong
   * @date 2017年7月4日
   * @param 
   * @throws
   * @return
   */
  public SysProfile getProfileByCode(String code);
  
}
