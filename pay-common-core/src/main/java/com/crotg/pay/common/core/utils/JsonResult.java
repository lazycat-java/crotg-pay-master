/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月14日
 */
package com.crotg.pay.common.core.utils;

import java.io.Serializable;

/**
 * 类功能描述:接口返回的数据格式出来 默认格式JSON
 * 
 * @author chengdongdong
 * @date 2017年3月14日
 * @version 1.0
 */
public class JsonResult implements Serializable {
  
  private static final long serialVersionUID = -627614638426042342L;

  
  // 错误码
  private String code;
  // 接口请求状态
  private boolean status;
  // 接口返回消息
  private String msg;
  // 接口返回的数据源
  private Object data;
  
  /**
   * 构造方法
   */
  public JsonResult(String code, boolean status, String msg, Object data) {
    super();
    this.code = code;
    this.status = status;
    this.msg = msg;
    this.data = data;
  }
  
  /**
   * 构造方法
   */
  public JsonResult() {
    super();
  }
  
  /**
   * 构造方法
   */
  public JsonResult(String code, boolean status, String msg) {
    super();
    this.code = code;
    this.status = status;
    this.msg = msg;
  }
  
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public boolean getStatus() {
    return status;
  }
  
  public void setStatus(boolean status) {
    this.status = status;
  }
  
  public String getMsg() {
    return msg;
  }
  
  public void setMsg(String msg) {
    this.msg = msg;
  }
  
  public Object getData() {
    return data;
  }
  
  public void setData(Object data) {
    this.data = data;
  }
  
}
