package cn.imethan.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * HttpClientUtils.java
 *
 * @author Ethan Wong
 * @time 2015年11月12日上午9:11:00
 */
public class HttpClientUtils {

	private final static int CONN_TIMEOUT = 10000;// 连接超时时间
	private final static int REQUEST_TIMEOUT = 10000;// 请求超时时间
	// private final static String RETURN_FOMATE = "json";// 支持json、xml

	/**
	 * 请求服务器入口
	 * 
	 * @param methodName
	 *            方法名
	 * @param map
	 *            输入参数集合
	 * @return
	 */
	public String doPost(String url, Map<String, String> map) {

		String result = null;
		HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(CONN_TIMEOUT); // 设置连接超时
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestBody(this.getPostParams(map));// 将表单的值放入postMethod中
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, REQUEST_TIMEOUT); // 设置post请求超时
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.getParams().setParameter("http.protocol.cookie-policy", "ignoreCookies");

		try {
			int statusCode = httpClient.executeMethod(postMethod);// 执行postMethod
			if (statusCode != HttpStatus.SC_OK) {// 如果请求返回的不是成功，则进行处理
				return new String(postMethod.getResponseBodyAsString());
			} else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
				// HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发301或者302
				// 从头中取出转向的地址
				Header locationHeader = postMethod.getResponseHeader("location");
				String location = null;
				if (locationHeader != null) {
					location = locationHeader.getValue();
					System.out.println("The page was redirected to:" + location);
				} else {
					System.err.println("Location field value is null.");
				}
				return result;
			} else {
				// result = new String(postMethod.getResponseBodyAsString());

				InputStream resStream = postMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
				StringBuffer resBuffer = new StringBuffer();
				String resTemp = "";
				while ((resTemp = br.readLine()) != null) {
					resBuffer.append(resTemp.trim());
				}
				result = resBuffer.toString();
			}
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}

	/**
	 * 设置表单值
	 * 
	 * @param map
	 * @return
	 */
	private NameValuePair[] getPostParams(Map<String, String> map) {
		NameValuePair[] data = new NameValuePair[map.size()];
		Iterator<String> it = map.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			data[i] = new NameValuePair(key, value);
			i++;
		}
		return data;
	}

}
