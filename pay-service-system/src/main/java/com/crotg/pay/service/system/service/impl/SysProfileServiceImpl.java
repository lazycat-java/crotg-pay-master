/*
 * Copyright 2017 Crotg-PAY Group.Date:2017年6月30日
 */
package com.crotg.pay.service.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crotg.pay.common.core.utils.StringUtils;
import com.crotg.pay.facaed.system.po.SysProfile;
import com.crotg.pay.service.system.dao.SysProfileDao;
import com.crotg.pay.service.system.global.GlobalCache;
import com.crotg.pay.service.system.service.SysProfileService;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年6月30日
 * @version 1.0
 */
@Service("sysProfileService")
public class SysProfileServiceImpl implements SysProfileService {
  
  Logger logger = LoggerFactory.getLogger(SysProfileServiceImpl.class);
  
  @Autowired
  private SysProfileDao sysProfileDao;
  
  /**
   * @author chengdongdong
   * @date 2017年6月30日
   */
  @Override
  public Map<String, Object> getAllProfile() {
    Map<String, Object> map = new HashMap<String, Object>();
    List<SysProfile> lists = sysProfileDao.getAllProfileCache();
    for (SysProfile sysProfile : lists) {
      map.put(sysProfile.getCode(), sysProfile.getValue());
    }
    return map;
  }

  /**
   * @author chengdongdong
   * @date 2017年7月4日
   */
  @Override
  public String getProfileByCode(String code) {
    String value = null;
    value = GlobalCache.getProfileByCode(code);
    logger.debug("缓存存在:value == "+value);
    if (StringUtils.isBlank(value)) {
      logger.debug("缓存没有,查询数据库");
      SysProfile profile = sysProfileDao.getProfileByCode(code);
      if (profile != null) {
        value = profile.getValue();
      }
    }
    return value;
  }
  
}
