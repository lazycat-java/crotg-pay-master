/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月13日
 */
package com.crotg.pay.common.core.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年3月13日
 * @version 1.0
 */
public class AES {
  
  /** 密钥算法 */
  private static final String KEY_ALGORITHM = "AES";
  
  /** 密算法/工作模式/填充方式 */
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
  
  /** 字符集 */
  public static final String CHAR_SET = "UTF-8";
  
  /**
   * 加密消息
   * 
   * @param message
   *          明文消息
   * @param key
   *          密钥，字符串长度必须为：16、24或32
   * @return 加密消息字节数组
   * @throws Exception
   */
  public static byte[] encrypt(String message, String key) throws Exception {
    byte[] data = null;
    try {
      SecretKey secretKey = new SecretKeySpec(key.getBytes(CHAR_SET), KEY_ALGORITHM);
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      data = cipher.doFinal(message.getBytes(CHAR_SET));
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return data;
  }
  
  /**
   * 加密消息
   * 
   * @param message
   *          明文消息
   * @param key
   *          密钥，字符串长度必须为：16、24或32
   * @return 密文（BASE64编码）
   * @throws Exception
   */
  public static String encryptToBase64(String message, String key) throws Exception {
    byte[] data = encrypt(message, key);
    return Base64.encodeBase64String(data);
  }
  
  /**
   * 解密
   * 
   * @param message
   *          待解密消息字节数组
   * @param key
   *          密钥，字符串长度必须为：16、24或32
   * @return 解密符串
   * @throws Exception
   */
  public static String decrypt(byte[] message, String key) throws Exception {
    String result = null;
    try {
      SecretKey secretKey = new SecretKeySpec(key.getBytes(CHAR_SET), KEY_ALGORITHM);
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      byte[] data = cipher.doFinal(message);
      result = new String(data);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;
  }
  
  public static SecretKey initKeyForAES(String key) throws NoSuchAlgorithmException {
    if (null == key || key.length() == 0) {
      throw new NullPointerException("key not is null");
    }
    SecretKeySpec key2 = null;
    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
    try {
      random.setSeed(key.getBytes(CHAR_SET));
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
      kgen.init(128, random);
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      key2 = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
    } catch (NoSuchAlgorithmException ex) {
      throw new NoSuchAlgorithmException();
    }
    return key2;
    
  }
  
  public static void main(String[] args) {
    try {
      String aa = AES.encryptToBase64("31431", "00c8f6c1dd279c9b2ab5d8122e7423ea");
      System.out.println(aa);
      System.out.println(AES.decrypt(Base64.decodeBase64(aa.getBytes()), "00c8f6c1dd279c9b2ab5d8122e7423ea"));
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}
