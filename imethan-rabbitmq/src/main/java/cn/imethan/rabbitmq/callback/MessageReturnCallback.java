package cn.imethan.rabbitmq.callback;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;


/**
 * MessageReturnCallback.java
 *
 * @author Ethan Wong
 * @time 2015年11月9日下午8:13:17
 */
public class MessageReturnCallback implements ReturnCallback {

	@Override
	public void returnedMessage(Message message, int replyCode,
			String replyText, String exchange, String routingKey) {
		System.out.println("[RabbitMQ] MessageReturnCallback message:"+message);
		System.out.println("[RabbitMQ] MessageReturnCallback replyCode:"+replyCode);
		System.out.println("[RabbitMQ] MessageReturnCallback replyText:"+replyText);
		System.out.println("[RabbitMQ] MessageReturnCallback exchange:"+exchange);
		System.out.println("[RabbitMQ] MessageReturnCallback routingKey:"+routingKey);
		
	}
}
