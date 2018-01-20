/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年5月8日
 */
package com.crotg.pay.trade.gateway.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.crotg.pay.common.core.utils.StringUtils;


/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年5月8日
 * @version 1.0
 */
public class ParseXmlUtils {
  
  /**
   * 解析微信服务器返回的xml 功能描述:
   * 
   * @author chengdongdong
   * @date 2017年5月8日
   * @param
   * @throws
   * @return
   */
  public static String doXml(String sxml, String key) {
    String str = null;
    if (StringUtils.isBlank(sxml)) {
      return str;
    }
    try {
      Document document = DocumentHelper.parseText(sxml);
      Element root = document.getRootElement();
      Element node = root.element(key);
      str = node.getText();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    return str;
    
  }
 
}
