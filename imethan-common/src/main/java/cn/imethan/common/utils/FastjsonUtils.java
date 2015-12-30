package cn.imethan.common.utils;

import java.util.ListIterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * FastjsonUtils.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月30日下午2:49:51
 */
public class FastjsonUtils {
	
	/**
	 * 过滤非必要字段信息
	 * @param object 待转换json对象
	 * @param strings 过滤字段名称
	 * @return
	 *
	 * @author Ethan Wong
	 * @datetime 2015年12月30日下午2:53:43
	 */
	public static String filterProperty(Object object,String...strings  ){
		Object json = JSON.parse(JSON.toJSONString(object));
		if(json instanceof JSONArray){
			JSONArray jsonArray = (JSONArray) json;
			ListIterator<Object> list = jsonArray.listIterator();
			while(list.hasNext()){
				JSONObject jsonObject = (JSONObject) list.next();
				 for(String string:strings){
					 jsonObject.remove(string.trim());
				 }
			}
			
			return jsonArray.toJSONString();
			
		}else{
			JSONObject jsonObject = (JSONObject)json;
			 for(String string:strings){
				 jsonObject.remove(string.trim());
			 }
			 return jsonObject.toJSONString();
		}
		
	}

}
