package cn.imethan.rabbitmq.listener;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;


/**
 * RecviveListener.java
 *
 * @author Ethan Wong
 * @time 2015年11月9日下午8:18:16
 */
@Service
public class SimpleRecviveListener {

	/**
	 * 消息监听
	 * 
	 * @param message 消息
	 *
	 * @author Ethan Wong
	 * @create-time 2015年5月9日 下午5:14:36
	 */
	public void listen(String message) {
		this.handle(message);
	}

	/**
	 * 消息监听
	 * 
	 * @param message 消息
	 * @throws UnsupportedEncodingException
	 *
	 * @author Ethan Wong
	 * @create-time 2015年5月9日 下午5:14:45
	 */
	public void listen(byte[] message) throws UnsupportedEncodingException {
		this.handle(new String(message, "UTF-8"));
	}

	/**
	 * 业务处理
	 * 
	 * @param message 消息
	 *
	 * @author Ethan Wong
	 * @create-time 2015年5月9日 下午5:15:03
	 */
	private void handle(String message) {
		System.out.println("[RabbitMQ] recv message:"+message);

	}

}
