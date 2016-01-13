package cn.imethan.jax.rs.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.alibaba.fastjson.JSON;

import cn.imethan.jax.rs.utils.IoUtils;

/**
 * CommonInterceptor.java
 *
 * @author Ethan Wong
 * @time 2015年11月13日下午1:50:57
 */
public class CommonInInterceptor extends AbstractPhaseInterceptor<Message> {
	
	private boolean isBase64 = true;//是否base64为编码
	
	
	public CommonInInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		//编码处理
		String contentType = (String) message.get(Message.CONTENT_TYPE);
		message.remove(Message.CONTENT_TYPE);
		message.put(Message.CONTENT_TYPE, contentType+";charset=UTF-8");

		/* 头部处理 */
		this.handleHeader(message);

		/* 请求参数处理 */
		String requestMethod = (String) message.get(Message.HTTP_REQUEST_METHOD);
		if (requestMethod.equalsIgnoreCase("get")) {
			
			this.handleGet(message);//处理请求类型为GET的参数
			
		} else if (requestMethod.equalsIgnoreCase("post")) {
			this.handlePost(message);//处理请求类型为POST的参数
		}
	}
	
	/**
	 * 头部处理
	 * @param message
	 *
	 * @author Ethan Wong
	 * @datetime 2015年11月16日上午11:11:41
	 */
	private void handleHeader(Message message){
		@SuppressWarnings("unchecked")
		TreeMap<String, List<String>> headers = (TreeMap<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);// 获取头部
		// TODO 这里可以运用头部进行额外的操作，例如接口的授权
		if (headers == null || headers.size() < 1) {
			throw new RuntimeException("没有Header,拦截器实施拦截");
		}
		
		//获取头部信息
//		List<String> keyList = headers.get("username");
//		if (keyList == null) {
//			CommonException commonException = new CommonException("用户名不正确");
//			throw commonException;
//		} else {
//			System.out.println("----------------keyList:" + keyList.get(0));
//		}
	}
	
	/**
	 * 处理POST方式的参数信息
	 * @param message
	 * @return
	 *
	 * @author Ethan Wong
	 * @throws IOException 
	 * @throws Exception 
	 * @datetime 2015年11月16日上午11:08:51
	 */
	private void handlePost(Message message)  {
		
		String queryString = "";
		
		InputStream inputStream = message.getContent(InputStream.class);
		if(inputStream !=null){
			//获取参数,并且进行base64为解密处理
			Map<String, Object> map = null;
			try {
				map = this.getParamsMap(IoUtils.getStringFromInputStream(inputStream));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			String data = (String) map.get("data");
			try {
				data = URLDecoder.decode(data, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			if(isBase64){
				try {
					data = new String(Base64.decodeBase64(data.trim()),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				map = JSON.parseObject(data.trim());
			}
			
			queryString = this.getParams(map);//重新生成queryString
			System.out.println("queryString:"+queryString.trim());
			if (queryString!=null) {
				message.setContent(InputStream.class, new ByteArrayInputStream(queryString.getBytes()));
			}
		}
	}
	
	/**
	 * 处理GET方式的参数信息
	 * @param message
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年11月16日上午11:09:16
	 */
	private String handleGet(Message message){
		String queryString = (String) MessageUtils.getContextualProperty(message, Message.QUERY_STRING, "");
		
		//获取参数
		Map<String,Object> map = this.getParamsMap(queryString);
		
//		map.put("appId", application.getId().toString());
		
		//重新生成queryString
		queryString = this.getParams(map);
		
		// TODO 这里可以进行参数的额外操作，例如加解密处理
		message.put(Message.QUERY_STRING, queryString);
		return queryString;
	}
	
	
	/**
	 * 参数解析
	 * @param strParams
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年11月14日 上午11:08:57
	 */
	private Map<String, Object> getParamsMap(String strParams) {
		if (strParams == null || strParams.trim().length() <= 0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String[] params = strParams.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] arr = params[i].split("=");
			
//			String data = arr[1].replace("%3D", "=");
//			data = data.replace("%2B", "+");
			
			map.put(arr[0], arr[1]);
		}
		return map;
	}
	
	/**
	 * 参数处理
	 * @param map
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年11月14日 上午11:08:49
	 */
	private String getParams(Map<String, Object> map) {
		if (map == null || map.size() == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = (String) map.get(key);
			
			/*
			 * 这里可以对客户端上送过来的输入参数进行特殊处理。如密文解密；对数据进行验证等等。。。
			 * if(key.equals("content")){ value.replace("%3D", "="); value =
			 * DesEncrypt.convertPwd(value, "DES"); }
			 */
			if (sb.length() <= 0) {
				sb.append(key + "=" + value);
			} else {
				sb.append("&" + key + "=" + value);
			}
		}
		return sb.toString();
	}

}