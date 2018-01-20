package com.crotg.pay.facaed.trade.po;

import java.io.Serializable;

/**
 * 类功能描述:微信统一下单实体类
 * 
 * @author chengdongdong
 * @date 2017年6月8日
 * @version 1.0
 */
public class UnifiedEntity implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 公众账号ID
   */
  private String appid;
  /**
   * 附加数据
   */
  private String attach;
  /**
   * 交易起始时间
   */
  private String time_start;
  
  /**
   * 交易结束时间
   */
  private String time_expire;
  /**
   * 商户号
   */
  private String mch_id;
  /**
   * 商品描述
   */
  private String body;
  /**
   * 标价金额
   */
  private long total_fee;
  /**
   * 商户订单号
   */
  private String out_trade_no;
  /**
   * 终端IP
   */
  private String spbill_create_ip;
  /**
   * 交易类型JSAPI，NATIVE，APP
   */
  private String trade_type;
  /**
   * 用户标识
   */
  private String openid;
  /**
   * 随机字符串
   */
  private String nonce_str;
  /**
   * 通知地址
   */
  private String notify_url;
  
  /**
   * 签名
   */
  private String sign;
  
  public String getSign() {
    return sign;
  }
  
  public void setSign(String sign) {
    this.sign = sign;
  }
  
  public String getAppid() {
    return appid;
  }
  
  public void setAppid(String appid) {
    this.appid = appid;
  }
  
  public String getAttach() {
    return attach;
  }
  
  public void setAttach(String attach) {
    this.attach = attach;
  }
  
  public String getTime_start() {
    return time_start;
  }
  
  public void setTime_start(String time_start) {
    this.time_start = time_start;
  }
  
  public String getTime_expire() {
    return time_expire;
  }
  
  public void setTime_expire(String time_expire) {
    this.time_expire = time_expire;
  }
  
  public String getMch_id() {
    return mch_id;
  }
  
  public void setMch_id(String mch_id) {
    this.mch_id = mch_id;
  }
  
  public String getBody() {
    return body;
  }
  
  public void setBody(String body) {
    this.body = body;
  }
  
  public long getTotal_fee() {
    return total_fee;
  }
  
  public void setTotal_fee(long total_fee) {
    this.total_fee = total_fee;
  }
  
  public String getOut_trade_no() {
    return out_trade_no;
  }
  
  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }
  
  public String getSpbill_create_ip() {
    return spbill_create_ip;
  }
  
  public void setSpbill_create_ip(String spbill_create_ip) {
    this.spbill_create_ip = spbill_create_ip;
  }
  
  public String getTrade_type() {
    return trade_type;
  }
  
  public void setTrade_type(String trade_type) {
    this.trade_type = trade_type;
  }
  
  public String getOpenid() {
    return openid;
  }
  
  public void setOpenid(String openid) {
    this.openid = openid;
  }
  
  public String getNonce_str() {
    return nonce_str;
  }
  
  public void setNonce_str(String nonce_str) {
    this.nonce_str = nonce_str;
  }
  
  public String getNotify_url() {
    return notify_url;
  }
  
  public void setNotify_url(String notify_url) {
    this.notify_url = notify_url;
  }
  
  /**
   * 构造函数
   */
  public UnifiedEntity(String appid, String attach, String time_start, String mch_id, String body, long total_fee, String out_trade_no, String spbill_create_ip, String trade_type,
      String openid, String nonce_str, String notify_url) {
    super();
    this.appid = appid;
    this.attach = attach;
    this.time_start = time_start;
    this.mch_id = mch_id;
    this.body = body;
    this.total_fee = total_fee;
    this.out_trade_no = out_trade_no;
    this.spbill_create_ip = spbill_create_ip;
    this.trade_type = trade_type;
    this.openid = openid;
    this.nonce_str = nonce_str;
    this.notify_url = notify_url;
  }
  
  public UnifiedEntity(String appid, String attach, String time_start, String mch_id, String body, long total_fee, String out_trade_no, String spbill_create_ip, String nonce_str,
      String notify_url) {
    super();
    this.appid = appid;
    this.attach = attach;
    this.time_start = time_start;
    this.mch_id = mch_id;
    this.body = body;
    this.total_fee = total_fee;
    this.out_trade_no = out_trade_no;
    this.spbill_create_ip = spbill_create_ip;
    this.nonce_str = nonce_str;
    this.notify_url = notify_url;
  }
  
  public UnifiedEntity() {
    super();
  }
  
}
