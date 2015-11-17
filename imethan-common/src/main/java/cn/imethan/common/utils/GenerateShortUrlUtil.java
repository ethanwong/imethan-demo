package cn.imethan.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * GenerateShortUrlUtil.java
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class GenerateShortUrlUtil {

	private static final String serverUrl = "http://dwz.cn/create.php";

	public static String generateShortUrl(String url) {
		String result = "";

		HttpClientUtils httpClientUtils = new HttpClientUtils();
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", url);

		String jsonStr = httpClientUtils.doPost(serverUrl, map);
		JSONObject object = JSON.parseObject(jsonStr);
		result = object.getString("tinyurl");

		Debug.println("generateShortUrl tinyurl:" + result);

		return result;
	}

	public static void main(String[] args) {
		generateShortUrl("http://localhost:8080/ticket-center-web/barcode/12345678901231");
	}

}
