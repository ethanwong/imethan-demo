package cn.imethan.web.struts2;
/**
 * MessageStore.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年12月31日上午9:39:12
 */
public class MessageStore {
	private String message;
	public MessageStore() {
		setMessage("Hello Struts User");
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageStore [message=" + message + "]";
	}	
}
