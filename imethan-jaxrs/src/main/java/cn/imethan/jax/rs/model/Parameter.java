package cn.imethan.jax.rs.model;

/**
 * Parameter.java
 *
 * @author Ethan Wong
 * @time 2015年11月12日下午8:43:14
 */
public class Parameter {
	
	private String username;
	private String result;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Parameter [username=" + username + ", result=" + result + "]";
	}
}