package com.crotg.pay.facaed.system.po;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class SysProfile implements Serializable {
  
  /**
	 *
	 **/
  private long id;
  
  /**
	 *
	 **/
  private String code;
  
  /**
	 *
	 **/
  private String value;
  
  /**
	 *
	 **/
  private String remark;
  
  /**
	 *
	 **/
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private java.util.Date createDate;
  
  public void setId(long id) {
    this.id = id;
  }
  
  public long getId() {
    return this.id;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  public String getRemark() {
    return this.remark;
  }
  
  public void setCreateDate(java.util.Date createDate) {
    this.createDate = createDate;
  }
  
  public java.util.Date getCreateDate() {
    return this.createDate;
  }
  
}
