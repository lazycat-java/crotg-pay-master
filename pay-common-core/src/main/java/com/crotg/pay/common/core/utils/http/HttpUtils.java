/*
 * 版权所有:深圳中讯智能信息技术有限公司日 期:2017年3月13日
 */
package com.crotg.pay.common.core.utils.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.crotg.pay.common.core.security.AES;
import com.crotg.pay.common.core.security.Encodes;
import com.crotg.pay.common.core.utils.StringUtils;

/**
 * 类功能描述:
 * 
 * @author chengdongdong
 * @date 2017年3月13日
 * @version 1.0
 */
public class HttpUtils {
	/** 公用HttpClient */
	private static CloseableHttpClient HttpClient;

	/** HTTP请求参数 */
	public static final RequestConfig HttpRequestConfig = RequestConfig
			.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();

	/** 默认字符集 */
	private static final String DEFAULT_CHAR_SET = "UTF-8";

	/** 日志记录器 */
	private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	static {
		try {
			// 信任所有的HTTPS证书
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] certs,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] certs,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} }, new SecureRandom());
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
					sslContext);
			HttpClient = HttpClientBuilder.create()
					.setSSLSocketFactory(sslSocketFactory).setMaxConnTotal(200)
					.setMaxConnPerRoute(200).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取真实的请求地址
	 * 
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static String getUrl(String url, Map<String, Object> params) {
		return getUrl(url, params, DEFAULT_CHAR_SET);
	}

	/**
	 * 获取真实的请求地址
	 * 
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getUrl(String url, Map<String, Object> params,
			String charSet) {
		StringBuffer sb = new StringBuffer(url);
		try {
			if ((params != null) && (params.size() > 0)) {
				if (url.contains("?"))
					sb.append("&");
				else
					sb.append("?");

				for (Entry<String, Object> entry : params.entrySet())
					sb.append(URLEncoder.encode(entry.getKey(), charSet))
							.append("=")
							.append(URLEncoder.encode(
									String.valueOf(entry.getValue()), charSet))
							.append("&");

				sb.deleteCharAt(sb.length() - 1);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * GET方式发送HTTP数据
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static HttpResult sendGet(String url, Map<String, Object> params) {
		return sendGet(url, params, DEFAULT_CHAR_SET);
	}

	/**
	 * GET方式发送HTTP数据
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static HttpResult sendGet(String url, Map<String, Object> params,
			String charSet) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;
		try {
			HttpGet httpGet = new HttpGet(getUrl(url, params));
			httpGet.setConfig(HttpRequestConfig);
			remoteResponse = HttpClient.execute(httpGet);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), charSet);
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				logger.error(
						"\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}",
						url, statusCode, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sendResult;
	}

	/**
	 * POST方式发送HTTP数据
	 * 
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static HttpResult sendPost(String url, Map<String, Object> params) {
		return sendPost(url, params, DEFAULT_CHAR_SET);
	}

	/**
	 * POST方式发送HTTP数据
	 * 
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static HttpResult sendPost(String url, Map<String, Object> params,
			String charSet) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(HttpRequestConfig);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : params.entrySet())
				nvps.add(new BasicNameValuePair(entry.getKey(), String
						.valueOf(entry.getValue())));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, charSet));

			remoteResponse = HttpClient.execute(httpPost);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), charSet);
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				logger.error(
						"\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}",
						url, statusCode, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sendResult;
	}

	/**
	 * 直接POST字符串数据
	 * 
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @return
	 */
	public static HttpResult sendPost(String url, String data) {
		return sendPost(url, data, DEFAULT_CHAR_SET);
	}

	/**
	 * 直接POST字符串数据
	 * 
	 * 
	 * @param url
	 *            目标地址
	 * @param params
	 *            参数集合
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static HttpResult sendPost(String url, String data, String charSet) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(HttpRequestConfig);
			httpPost.setEntity(new StringEntity(data, charSet));

			remoteResponse = HttpClient.execute(httpPost);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), charSet);
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				logger.error(
						"\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}",
						url, statusCode, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sendResult;
	}

	public static HttpResult wxSSLSendPost(String url, String data,
			String mchId, String sslPath) {
		HttpResult sendResult = null;
		int statusCode;
		String result;
		CloseableHttpResponse remoteResponse = null;

		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(sslPath));
			try {
				keyStore.load(instream, mchId.toCharArray());
			} finally {
				instream.close();
			}

			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, mchId.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom()
					.setSSLSocketFactory(sslsf).build();

			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(data.toString(), "UTF-8"));
			remoteResponse = httpclient.execute(httpPost);

			statusCode = remoteResponse.getStatusLine().getStatusCode();
			result = EntityUtils.toString(remoteResponse.getEntity(), "UTF-8");
			sendResult = new HttpResult(statusCode, result);
			if (statusCode != 200) {
				logger.error(
						"\n数据发送失败\n[URL]：{}\n[StatusCode]：{}\n[Result]：\n{}",
						url, statusCode, result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (remoteResponse != null) {
					remoteResponse.close();
					remoteResponse = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sendResult;

	}

	/**
	 * 读取传入的字符串，可用于读取XML
	 * 
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestDate(HttpServletRequest request) {
		return getRequestDate(request, DEFAULT_CHAR_SET);
	}

	/**
	 * 读取传入的字符串，可用于读取XML
	 * 
	 * 
	 * @param request
	 * @param charSet
	 *            字符编码
	 * @return
	 */
	public static String getRequestDate(HttpServletRequest request,
			String charSet) {
		StringBuffer sb = new StringBuffer();
		try {
			ServletInputStream sis = request.getInputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = sis.read(buffer, 0, buffer.length)) != -1)
				sb.append(new String(buffer, 0, len, charSet));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * 获取客户端的真实IP
	 * 
	 * @date 2015年4月28日
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");

		if (StringUtils.isBlank(ip) || "unknown".equals(ip))
			ip = request.getHeader("X-Forwarded-For");

		if (StringUtils.isBlank(ip) || "unknown".equals(ip))
			ip = request.getHeader("Proxy-Client-IP");

		if (StringUtils.isBlank(ip) || "unknown".equals(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");

		if (StringUtils.isBlank(ip) || "unknown".equals(ip))
			ip = request.getHeader("HTTP_CLIENT_IP");

		if (StringUtils.isBlank(ip) || "unknown".equals(ip))
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");

		if (StringUtils.isBlank(ip) || "unknown".equals(ip))
			ip = request.getRemoteAddr();

		return ip;
	}

	/**
	 * 获取Cookie的值
	 * 
	 * @date 2015年4月5日
	 * @param name
	 *            名称
	 * @param request
	 * @return
	 */
	public static String getCookie(String name, HttpServletRequest request) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					value = cookie.getValue();
					break;
				}
			}
		}

		return value;
	}

	/**
	 * 设置Cookie的值
	 * 
	 * @date 2015年4月4日
	 * @param request
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param path
	 *            路径
	 * @param time
	 *            最大存活时间，单位：秒
	 * @return
	 */
	public static void setCookie(String name, String value, String path,
			Integer time, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(path);
		cookie.setMaxAge(time);
		response.addCookie(cookie);
	}

	/**
	 * 删除Cookie
	 * 
	 * @date 2015年4月5日
	 * @param name
	 *            名称
	 * @param request
	 */
	public static void deleteCookie(String name, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setMaxAge(0);
					break;
				}
			}
		}
	}

	/**
	 * 设置表单Token
	 * 
	 * @date 2015年4月10日
	 * @param request
	 * @param key
	 * @return
	 */
	public static String setFormToken(HttpServletRequest request, String key) {
		String token = null;
		try {
			token = AES.encryptToBase64(Encodes.salt(), key);
			request.getSession().setAttribute("formToken", token);
			request.setAttribute("formToken", token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return token;
	}

	/**
	 * 校验表单Token
	 * 
	 * @date 2015年4月8日
	 * @param request
	 * @return
	 */
	public static Boolean checkFormToken(HttpServletRequest request) {
		boolean pass = false;
		String formToken = request.getParameter("formToken");
		String sessionToken = (String) request.getSession().getAttribute(
				"formToken");
		if (StringUtils.isNotBlank(formToken)
				&& StringUtils.isNotBlank(sessionToken))
			pass = formToken.equals(sessionToken);
		request.getSession().removeAttribute("formToken");
		return pass;
	}

	/**
	 * 向客户端写入JSON字符串
	 * 
	 * @date 2015年4月23日
	 * @param message
	 *            数据对象
	 * @param response
	 */
	public static void printJSON(Object message, HttpServletResponse response) {
		printJSON(JSON.toJSONString(message), response);
	}

	/**
	 * 向客户端写入JSON字符串
	 * 
	 * @date 2015年3月6日
	 * @param jsonString
	 *            JSON字符串
	 * @param response
	 */
	public static void printJSON(String jsonString, HttpServletResponse response) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向客户端写入字符串
	 * 
	 * @param message
	 *            字符串
	 * @param response
	 */
	public static void printText(String message, HttpServletResponse response) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
