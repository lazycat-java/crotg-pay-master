/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月13日
 */
package com.crotg.pay.common.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * JAVA版本的自动生成有规则的订单号(或编号) 生成的格式是: 200908010001 前面几位为当前的日期,后面五位为系统自增长类型的编号 原理:
 * 1.获取当前日期格式化值; 2.读取文件,上次编号的值+1最为当前此次编号的值 (新的一天会重新从1开始编号)
 */
public class OrderNoUtils {
  
  /**
   * 时间格式化
   */
  protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  
  /**
   * 产生唯一 的序列号。
   * 
   * @return String
   */
  public static String getSerialNumber() {
    int hashCode = UUID.randomUUID().toString().hashCode();
    if (hashCode < 0) {
      hashCode = -hashCode;
    }
    return sdf.format(new Date()).substring(0, 8) + String.format("%010d", hashCode);
  }
  
  public static void main(String[] args) {
	System.out.println(OrderNoUtils.getSerialNumber());
}
}
