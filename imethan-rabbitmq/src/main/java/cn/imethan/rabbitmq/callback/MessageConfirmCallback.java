package cn.imethan.rabbitmq.callback;

import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * MessageConfirmCallback.java
 *
 * @author Ethan Wong
 * @time 2015年11月9日下午8:13:11
 */
public class MessageConfirmCallback implements ConfirmCallback {

	@Override
	public void confirm(CorrelationData correlationData, boolean ack,String cause) {
		
		System.out.println("[RabbitMQ] MessageConfirmCallback correlationData:"+correlationData);
		System.out.println("[RabbitMQ] MessageConfirmCallback             ack:"+ack);
		System.out.println("[RabbitMQ] MessageConfirmCallback           cause:"+cause);
		
		if(ack){
			System.out.println("[RabbitMQ] send message to rabbitmq success !");
		}else{
			System.out.println("[RabbitMQ] send message to rabbitmq fail !");
		}
	}

}
