package com.crotg.pay.facaed.trade.exception;

import com.crotg.pay.common.core.utils.JsonResult;

/**
 * <b>功能说明:交易模块异常类</b>
 */
public class TradeException extends Exception {
  
  private static final long serialVersionUID = 1L;
  
  public static final String BIZ_SUCCESS = "10000";
  
  /** 商户支付配置有误 */
  public static final JsonResult MERCHANTCONFIG_ERROR = new JsonResult("10040001", false, "商户支付配置有误");
  
  /** 请求远程服务器错误 */
  public static final JsonResult REQUEST_ERROR = new JsonResult("10040002", false, "请求远程服务器错误");
  
  /** 支付订单已经存在 */
  public static final JsonResult TRADE_ORDER_EXIST = new JsonResult("10040003", false, "支付订单已经存在,请勿重复添加");
  
  /** 订单失效时间配置错误 */
  public static final JsonResult TRADE_ORDER_EXPIRE_ERROR = new JsonResult("10040004", false, "订单失效时间异常");
  
  /** 订单添加失败 */
  public static final JsonResult TRADE_ORDER_INSERT_ERROR = new JsonResult("10040005", false, "订单添加失败");
  
  /** 无效商户号 */
  public static final JsonResult TRADE_MERCHANT_NO_ERROR = new JsonResult("10040006", false, "无效的商户号");
  
  /** 商户号不存在 */
  public static final JsonResult TRADE_MERCHANT_NO_NOTEXIST = new JsonResult("10040007", false, "商户号不存在");
  
  /** 无效交易描述 */
  public static final JsonResult TRADE_DESC_ERROR = new JsonResult("10040008", false, "无效交易描述");
  
  /** 无效的商户订单号 */
  public static final JsonResult TRADE_MERCHANT_ORDERNO_ERROR = new JsonResult("10040009", false, "无效的商户订单号");
  
  /** 无效的支付金额 */
  public static final JsonResult TRADE_TOTAL_FEE_ERROR = new JsonResult("10040010", false, "无效的支付金额");
  
  /** 无效的客户端IP */
  public static final JsonResult TRADE_CREATE_IP_ERROR = new JsonResult("10040011", false, "无效的客户端IP");
  
  /** 无效的支付通道 */
  public static final JsonResult TRADE_PAY_PASS_ERROR = new JsonResult("10040012", false, "无效的支付通道");
  
  /** 无效的openid */
  public static final JsonResult TRADE_PAY_OPENID_ERROR = new JsonResult("10040013", false, "无效的openid");
  
  /** 无效的通知地址 */
  public static final JsonResult TRADE_CALL_URL_ERROR = new JsonResult("10040014", false, "无效的通知地址");
  
  /** 系统异常 */
  public static final JsonResult TRADE_PAY_ERROR = new JsonResult("10040015", false, "系统异常");
  
  /** 无效订单超时时间 */
  public static final JsonResult TRADE_EXPIRE_ERROR = new JsonResult("10040016", false, "无效订单超时时间");
  
  /** 交易订单状态异常 */
  public static final JsonResult TRADE_ORDER_STATUS_ERROR = new JsonResult("10040017", false, "交易订单状态异常");
  
  /** 交易订单超时失效 */
  public static final JsonResult TRADE_ORDER_EXPIRED = new JsonResult("10040018", false, "交易订单超时失效");
  
  /** 统一下单失败 */
  public static final String TRADE_PREPAY_ERROR = "10040019";
  
}
