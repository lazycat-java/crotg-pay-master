package com.crotg.pay.common.core.security;

import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 单向加密工具类
 *
 */
public class Encodes {
  
  /** 随机字符集合 */
  private static String NONCE_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  
  /** 安全的随机数生成器 */
  private static SecureRandom random = new SecureRandom();
  
  /** 密码salt字节数组长度 */
  private static int SALT_SIZE = 8;
  
  /** 密码加密类型 */
  public static final String PASSWORD_ALGORITHM = "SHA-1";
  
  /** 密码散列次数 */
  public static int PASSWORD_INTERATIONS = 1024;
  
  /**
   * 对字符串进行MD5加密
   * 
   * @param message
   * @return
   */
  public static String md5(String message) {
    return DigestUtils.md5Hex(message);
  }
  
  /**
   * 对字节数组进行MD5加密
   * 
   * @param data
   * @return
   */
  public static String md5(byte[] data) {
    return DigestUtils.md5Hex(data);
  }
  
  /**
   * 对字符串进行简单的SHA1加密
   * 
   * @param message
   * @return
   */
  public static String sha1(String message) {
    return DigestUtils.sha1Hex(message);
  }
  
  /**
   * 对字节数组进行简单的SHA1加密
   * 
   * @param message
   * @return
   */
  public static byte[] sha1(byte[] message) {
    return DigestUtils.sha1(message);
  }
  
  /**
   * 对字符串进行简单的SHA256加密
   * 
   * @param message
   * @return
   */
  public static String sha256(String message) {
    return DigestUtils.sha256Hex(message);
  }
  
  /**
   * 对字节数组进行简单的SHA256加密
   * 
   * @param message
   * @return
   */
  public static byte[] sha256(byte[] message) {
    return DigestUtils.sha256(message);
  }
  
  /**
   * Hex编码
   * 
   * @param data
   * @return
   */
  public static String hexEncode(byte[] data) {
    return Hex.encodeHexString(data);
  }
  
  /**
   * Hex解码
   * 
   * @param message
   * @return
   */
  public static byte[] hexDecode(String message) {
    byte[] result = null;
    try {
      result = Hex.decodeHex(message.toCharArray());
    } catch (DecoderException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  /**
   * 生成随机salt
   * 
   * @param saltSize
   * @return
   */
  public static String salt() {
    byte[] bytes = new byte[SALT_SIZE];
    random.nextBytes(bytes);
    return hexEncode(bytes);
  }
  
  /**
   * 生成安全密码
   * 
   * @param message
   *          源数据
   * @param salt
   *          加盐
   * @return
   */
  public static String password(String password, String salt) {
    MessageDigest digest = DigestUtils.getSha1Digest();
    digest.update(hexDecode(salt));
    
    byte[] result = digest.digest(password.getBytes());
    
    for (int i = 1; i < PASSWORD_INTERATIONS; i++) {
      digest.reset();
      result = digest.digest(result);
    }
    
    return Hex.encodeHexString(result);
  }
  
  /**
   * 获取随机字符串
   * 
   * @param length
   *          字符串长度
   * @return
   */
  public static String nonceString(Integer length) {
    int index;
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      index = random.nextInt(62);
      sb.append(NONCE_STRING.charAt(index));
    }
    return sb.toString();
  }
  
  /**
   * 生成验证码：6位整数
   * 
   * @return
   */
  public static String verifyCode() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 6; i++)
      sb.append(random.nextInt(10));
    
    return sb.toString();
  }
  
  public static void main(String args[]) {
    
    System.out.println(Encodes.nonceString(32));
  }
}
