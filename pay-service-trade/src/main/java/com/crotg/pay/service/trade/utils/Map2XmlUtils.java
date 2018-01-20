package com.crotg.pay.service.trade.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Map2XmlUtils {
  
  @SuppressWarnings("rawtypes")
  public static String mapToXMLTest2(Map<String, Object> map) {
    StringBuffer sb = new StringBuffer();
    sb.append("<xml>");
    Set set = map.keySet();
    for (Iterator it = set.iterator(); it.hasNext();) {
      String key = (String) it.next();
      Object value = map.get(key);
      if (null != value) {
        sb.append("<" + key + ">" + value + "</" + key + ">");
      }
    }
    sb.append("</xml>");
    return sb.toString();
  }
  
}
