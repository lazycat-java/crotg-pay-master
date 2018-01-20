/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月13日
 */
package com.crotg.pay.common.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年3月13日
 * @version 1.0
 */
public class StringUtils {

	/** 正则表达式：手机号 */
	public static final String REGEX_MOBILE = "^((1[3-8][0-9]))\\d{8}$";

	/** 正则表达式：邮件地址 */
	public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

	/** 正则表达式：URL地址 */
	public static final String REGEX_URL = "[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*";

	/** 正则表达式：身份证号18位 */
	public static final String REGEX_ID_CARD = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

	/** 正则表达式：身份证号15位 */
	public static final String REGEX_ID_CARD_OLD = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";

	/** 正则表达式：整数 */
	public static final String REGEX_NUMBER = "\\d*";

	/** 正则表达式：包含中文 */
	public static final String REGEX_CHINESS = "^.*[\\u4e00-\\u9fa5]+.*$";

	/** 正则表达式：全为中文 */
	public static final String REGEX_CHINESS_ALL = "[\\u4e00-\\u9fa5]{2,25}";

	/** 正则表达式：手机浏览器 */
	public static final String REGEX_UA_PHONE = "\\b(ip(hone|od)|android|opera m(ob|in)i|windows (phone|ce)|blackberry|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp|laystation portable)|nokia|fennec|htc[-_]|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	/** 正则表达式：平板浏览器 */
	public static final String REGEX_UA_TAB = "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

	/** 正则表达式：用户名（6到16） */
	public static String REGEX_USER_NAME = "^([a-zA-Z0-9_]){6,16}$";

	/** 正则表达式：密码（8到20） */
	public static String REGEX_PASSWORD = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%\\^&*?]{8,20}$";

	private static final Pattern Mobile = Pattern.compile(REGEX_MOBILE);
	private static final Pattern Email = Pattern.compile(REGEX_EMAIL);
	private static final Pattern Url = Pattern.compile(REGEX_URL);
	private static final Pattern IDcard = Pattern.compile(REGEX_ID_CARD);
	private static final Pattern IDcardOld = Pattern.compile(REGEX_ID_CARD_OLD);
	private static final Pattern Number = Pattern.compile(REGEX_NUMBER);
	private static final Pattern Chiness = Pattern.compile(REGEX_CHINESS);
	private static final Pattern ChinessAll = Pattern
			.compile(REGEX_CHINESS_ALL);
	private static final Pattern FromPhone = Pattern.compile(REGEX_UA_PHONE);
	private static final Pattern FromTab = Pattern.compile(REGEX_UA_TAB);
	private static Pattern UserName = Pattern.compile(REGEX_USER_NAME);
	private static Pattern Password = Pattern.compile(REGEX_PASSWORD);

	/**
	 * 首字母变小写
	 * 
	 * @date 2014年10月24日
	 * @param str
	 * @return
	 */
	public static String firstCharToLowerCase(final String str) {
		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		return Character.toLowerCase(firstChar) + tail;
	}

	/**
	 * 首字母变大写
	 * 
	 * @date 2014年10月24日
	 * @param str
	 * @return
	 */
	public static String firstCharToUpperCase(final String str) {
		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		return Character.toUpperCase(firstChar) + tail;
	}

	/**
	 * 转换为数据库风格（全部小写，下划线分割）
	 * 
	 * @date 2015年9月1日
	 * @param str
	 * @return
	 */
	public static String toDbStyle(String str) {
		StringBuffer sb = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (Character.isLowerCase(c))
				sb.append(c);
			else {
				if (i > 0)
					sb.append("_");
				sb.append(Character.toLowerCase(c));
			}

		}
		return sb.toString();
	}

	/**
	 * 转换为Java
	 * 
	 * @date 2015年9月1日
	 * @param str
	 * @return
	 */
	public static String toJavaStyle(String str) {
		boolean cahang = false;
		StringBuffer sb = new StringBuffer();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c == '_') {
				cahang = true;
			} else {
				if (cahang) {
					c = Character.toUpperCase(c);
					cahang = false;
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 获取字符串的UTF-8字节数组
	 * 
	 * @param message
	 * @return
	 */
	public static byte[] getBytesUtf8(String message) {
		return org.apache.commons.codec.binary.StringUtils
				.getBytesUtf8(message);
	}

	/**
	 * 获取字符串的Base64字节数组
	 * 
	 * @param message
	 * @return
	 */
	public static byte[] getBytesBase64(String message) {
		return Base64.decodeBase64(message);
	}

	/**
	 * 根据字节数组构造UTF-8字符串
	 * 
	 * @param message
	 * @return
	 */
	public static String getStringUtf8(byte[] message) {
		String result = null;
		try {
			result = new String(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据字节数组构造Base64字符串
	 * 
	 * @param message
	 * @return
	 */
	public static String getStringBase64(byte[] message) {
		return Base64.encodeBase64String(message);
	}

	/**
	 * 设定校验用户名及密码的正则表达式，方便扩展
	 * 
	 * @date 2015年8月14日
	 * @param userName
	 * @param password
	 */
	public static void setUserNameAndPasswordRegex(String userName,
			String password) {
		REGEX_USER_NAME = userName;
		UserName = Pattern.compile(REGEX_USER_NAME);

		REGEX_PASSWORD = password;
		Password = Pattern.compile(REGEX_PASSWORD);
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return 空返回false，非空返回true
	 */
	public static Boolean isNotBlank(String str) {
		return org.apache.commons.lang.StringUtils.isNotBlank(str);
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return 空返回true，非空返回false
	 */
	public static Boolean isBlank(String str) {
		return org.apache.commons.lang.StringUtils.isBlank(str);
	}

	/**
	 * 截取标识符之前的字符串
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringBefore(String str, String separator) {
		return org.apache.commons.lang.StringUtils.substringBefore(str,
				separator);
	}

	/**
	 * 截取标识符之后的字符串
	 * 
	 * @date 2015年4月10日
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringAfter(String str, String separator) {
		return org.apache.commons.lang.StringUtils.substringAfter(str,
				separator);
	}

	/**
	 * 截取最后一个标识符之后的字符串
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String substringAfterLast(String str, String separator) {
		return org.apache.commons.lang.StringUtils.substringAfterLast(str,
				separator);
	}

	/**
	 * 截取字符串
	 * 
	 * @param str
	 *            原始字符串
	 * @param start
	 *            起始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static String substring(String str, Integer start, Integer end) {
		return org.apache.commons.lang.StringUtils.substring(str, start, end);
	}

	/**
	 * 清楚空字符
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static String trimToEmpty(String str) {
		return org.apache.commons.lang.StringUtils.trimToEmpty(str);
	}

	/**
	 * 判断字符串是否符合用户名格式
	 * 
	 * @date 2015年5月25日
	 * @param str
	 * @return
	 */
	public static Boolean isUserName(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = UserName.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断字符串是否符合密码格式
	 * 
	 * @date 2015年5月25日
	 * @param str
	 * @return
	 */
	public static Boolean isPassword(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = Password.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断字符串是否符合手机号格式
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static Boolean isMobile(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = Mobile.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断字符串是否符合邮件地址格式
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static Boolean isEmail(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = Email.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断字符串是否为URL
	 * 
	 * @date 2015年4月18日
	 * @param str
	 * @return
	 */
	public static Boolean isUrl(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = Url.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断字符串是否符合身份证格式
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static Boolean isIdCard(String str) {
		Boolean matcher = false;
		if (isNotBlank(str)) {
			if (str.length() == 18)
				matcher = IDcard.matcher(str).matches();
			else if (str.length() == 15)
				matcher = IDcardOld.matcher(str).matches();
		}
		return matcher;
	}

	/**
	 * 判断字符串是否为纯数字
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static Boolean isNumber(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = Number.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断字符串是否为纯中文
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static Boolean isChiness(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = ChinessAll.matcher(str).matches();
		return matcher;
	}

	/**
	 * 判断当前请求是否来自移动端
	 * 
	 * @date 2015年4月10日
	 * @param ua
	 * @return
	 */
	public static Boolean fromMobile(String ua) {
		Boolean matcher = false;
		if (isNotBlank(ua)) {
			matcher = FromPhone.matcher(ua).matches();
			if (!matcher)
				matcher = FromTab.matcher(ua).matches();
		}
		return matcher;
	}

	/**
	 * 判断字符串是否包含中文
	 * 
	 * @date 2015年4月9日
	 * @param str
	 * @return
	 */
	public static Boolean containChiness(String str) {
		Boolean matcher = false;
		if (isNotBlank(str))
			matcher = Chiness.matcher(str).matches();
		return matcher;
	}

	/**
	 * 去除字符串中的Emoji字符
	 * 
	 * @date 2015年3月29日
	 * @param content
	 * @return
	 */
	public static String cleanEmoji(String content) {
		if (containsEmoji(content)) {
			StringBuilder sb = new StringBuilder();
			int len = content.length();
			for (int i = 0; i < len; i++) {
				char character = content.charAt(i);
				if (notEmoji(character))
					sb.append(character);
			}
			content = sb.toString();
		}
		return content;
	}

	/**
	 * 判断字符串中数否包含Emoji字符串
	 * 
	 * @date 2015年3月29日
	 * @param content
	 * @return
	 */
	public static Boolean containsEmoji(String content) {
		boolean containsEmoji = false;
		if (StringUtils.isNotBlank(content)) {
			int len = content.length();
			for (int i = 0; i < len; i++) {
				containsEmoji = !notEmoji(content.charAt(i));
				if (containsEmoji)
					break;
			}
		}
		return containsEmoji;
	}

	/**
	 * 判断字符是否为Emoji
	 * 
	 * @date 2015年3月29日
	 * @param content
	 * @return
	 */
	private static Boolean notEmoji(char content) {
		Boolean notEmoji = (content == 0x0) || (content == 0x9)
				|| (content == 0xA) || (content == 0xD)
				|| ((content >= 0x20) && (content <= 0xD7FF))
				|| ((content >= 0xE000) && (content <= 0xFFFD))
				|| ((content >= 0x10000) && (content <= 0x10FFFF));
		return notEmoji;
	}

	public static String fillStringByArgs(String str, String[] arr) {
		Matcher m = Pattern.compile("\\{(\\d)\\}").matcher(str);
		while (m.find()) {
			str = str.replace(m.group(), arr[Integer.parseInt(m.group(1))]);
		}
		return str;
	}

}
