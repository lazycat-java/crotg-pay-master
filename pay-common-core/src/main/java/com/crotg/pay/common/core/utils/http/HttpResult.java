/*
 *版权所有:深圳中讯智能信息技术有限公司
 *日        期:2017年3月13日
 * */
package com.crotg.pay.common.core.utils.http;

/**
 * 类功能描述:
 * @author chengdongdong
 * @date 2017年3月13日
 * @version 1.0
 */
/**
 * HTTP发送反馈
 *
 */
public class HttpResult {
  /** 状态码 */
  private Integer statusCode;
  
  /** 返回内容 */
  private String content;
  
  public HttpResult(Integer statusCode, String content) {
    super();
    this.statusCode = statusCode;
    this.content = content;
  }
  
  public Integer getStatusCode() {
    return statusCode;
  }
  
  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }
  
  public String getContent() {
    return content;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
}

