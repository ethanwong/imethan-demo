package cn.imethan.jax.rs.interceptor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import cn.imethan.jax.rs.exception.AuthException;
import cn.imethan.jax.rs.utils.IoUtils;

/**
 * AuthInterceptor.java
 *
 * @author Ethan Wong
 * @time 2015年11月13日下午1:50:57
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<Message> {
	
	public AuthInterceptor() {
		super(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		/* 头部处理 */
		this.handleHeader(message);

		/* 请求参数处理 */
		String requestMethod = (String) message.get(Message.HTTP_REQUEST_METHOD);
		if (requestMethod.equalsIgnoreCase("get")) {
			this.handleGet(message);//GET参数处理
		} else if (requestMethod.equalsIgnoreCase("post")) {
			this.handlePost(message);//POST参数处理
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
		// 获取头部
		@SuppressWarnings("unchecked")
		TreeMap<String, List<String>> headers = (TreeMap<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
		// TODO 这里可以运用头部进行额外的操作，例如接口的授权
		if (headers == null || headers.size() < 1) {
			throw new AuthException("没有Header,拦截器实施拦截");
		}
		List<String> usernameList = headers.get("username");//获取头部信息
		if (headers.get("username") == null) {
			AuthException authException = new AuthException("用户名不正确");
			throw authException;
		} else {
			System.out.println("----------------username:" + usernameList.get(0));
		}
	}
	
	/**
	 * 处理POST方式的参数信息
	 * @param message
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年11月16日上午11:08:51
	 */
	private void handlePost(Message message){
		String queryString= "";
		//处理请求类型为POST的参数
		try {
			InputStream inputStream = message.getContent(InputStream.class);
			if (inputStream != null) {
				queryString = this.getParams(this.getParamsMap(IoUtils.getStringFromInputStream(inputStream)));
				
				// TODO 这里可以进行参数的额外操作，例如加解密处理
				message.setContent(InputStream.class, new ByteArrayInputStream(queryString.getBytes()));
			}
		} catch (Exception e) {

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
	private void handleGet(Message message){
		String queryString = (String) MessageUtils.getContextualProperty(message, Message.QUERY_STRING, "");
		
		// TODO 这里可以进行参数的额外操作，例如加解密处理
		message.put(Message.QUERY_STRING, queryString);
		
	}
	
	
	/**
	 * 参数解析
	 * @param strParams
	 * @return
	 *
	 * @author Ethan
	 * @datetime 2015年11月14日 上午11:08:57
	 */
	private Map<String, String> getParamsMap(String strParams) {
		if (strParams == null || strParams.trim().length() <= 0) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		String[] params = strParams.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] arr = params[i].split("=");
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
	private String getParams(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			/*
			 * 这里可以对客户端上送过来的输入参数进行特殊处理。如密文解密；对数据进行验证等等。。。
			 * if(key.equals("content")){ value.replace("%3D", "="); value =
			 * DesEncrypt.convertPwd(value, "DES"); }
			 */
			if (sb.length() <= 0) {
				sb.append(key + "=" + value+"lalallal");
			} else {
				sb.append("&" + key + "=" + value+"lalallal");
			}
		}
		return sb.toString();
	}

}