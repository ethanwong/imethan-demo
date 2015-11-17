package cn.imethan.jax.rs.exception;

/**
 * AuthException.java
 *
 * @author Ethan Wong
 * @time 2015年11月13日下午4:07:21
 */
public class AuthException extends RuntimeException {
	
	private static final long serialVersionUID = -4294982322051778315L;


	public AuthException(String message) {
		super( message );
	}

	public AuthException() {
		super();
	}

	public AuthException(String message, Throwable cause) {
		super( message, cause );
	}

	public AuthException(Throwable cause) {
		super( cause );
	}
}

