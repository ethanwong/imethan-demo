package cn.imethan.jax.rs.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Result.java
 *
 * @author Ethan Wong
 * @time 2015年11月12日下午8:08:10
 */
@JacksonXmlRootElement(localName = "result")
public class Result {
	
	@JacksonXmlProperty(localName="succeed")
	private Boolean succeed = false;//是否成功标识
	private Integer code = 0;	
	@JacksonXmlProperty(localName="message")
	private String message = "操作失败！";//提示消息
	
	public Result(){
	}
	
	public Result(Boolean succeed,String message){
		this.succeed = succeed;
		this.message = message;
	}
	
	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}