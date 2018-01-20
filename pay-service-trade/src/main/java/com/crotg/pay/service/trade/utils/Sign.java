package com.crotg.pay.service.trade.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.crotg.pay.common.core.security.Encodes;
import com.crotg.pay.common.core.utils.StringUtils;

public class Sign {
  
  /**
   * 获取签名
   * 
   * @param map
   * @param key
   * @return
   */
  @SuppressWarnings("rawtypes")
  public static String getSign(Map<String, Object> map, String key) {
    StringBuffer sb = new StringBuffer();
    Set es = new TreeMap<String, Object>(map).entrySet();
    Iterator it = es.iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      String k = (String) entry.getKey();
      Object v = entry.getValue();
      if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
        sb.append(k + "=" + v + "&");
      }
    }
    if (StringUtils.isNotBlank(key)) {
      sb.append("key=" + key);
    } else {
      sb.deleteCharAt(sb.toString().length() - 1);
    }
    String sign = Encodes.md5(sb.toString());
    return sign;
  }
  
}
