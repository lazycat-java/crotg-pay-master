/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月17日
 */
package com.crotg.pay.common.core.utils;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

public class MapUtils {
  
  /**
   * 功能描述:将bean实体类转成map
   * 
   * @author wangbaofeng
   * @date 2017年3月17日
   * @param
   * @throws
   * @return
   */
  public static Map<String, Object> beanToMap(Object obj) {
    Map<String, Object> params = new HashMap<String, Object>(0);
    try {
      PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
      PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
      for (int i = 0; i < descriptors.length; i++) {
        String name = descriptors[i].getName();
        if (!"class".equals(name)) {
          if (propertyUtilsBean.getNestedProperty(obj, name) != null) {
            
            params.put(name, propertyUtilsBean.getNestedProperty(obj, name));
          }
          
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return params;
  }
  
}
