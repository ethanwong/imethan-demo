package cn.imethan.rabbitmq.sender;

import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RabbitMqSender.java
 *
 * @author Ethan Wong
 * @time 2015年11月9日下午8:21:25
 */
@Service
public class RabbitMqSender {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 发送消息
	 * @param queue
	 * @param content
	 *
	 * @author Ethan
	 * @datetime 2015年11月9日 下午8:24:00
	 */
	public void sendMessage(String queue,String content){
		try {
			String routingKey = queue;
			//发送消息
			rabbitTemplate.setQueue(queue);
			rabbitTemplate.setRoutingKey(routingKey);
			rabbitTemplate.send(this.generateMessage(content,queue));
		} catch (AmqpException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成消息
	 * @param content 消息内容
	 * @param queueName 队列名称
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月6日 下午4:20:50
	 */
	private Message generateMessage(String content,String queueName){
		
		String appId = rabbitTemplate.getConnectionFactory().getVirtualHost()+"/"+queueName;//将虚拟主机和队列名称设置为appId
		
		//发送消息
		MessageProperties props = MessagePropertiesBuilder.newInstance()
				.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
				.setContentEncoding("UTF-8")
				.setTimestamp(new Date()).setHeader("", "")
				.setAppId(appId)//设置发送中心系统识别
				.build();
		Message message = MessageBuilder.withBody(content.getBytes())
			.andProperties(props)
			.build();
		return message;
	}
}
