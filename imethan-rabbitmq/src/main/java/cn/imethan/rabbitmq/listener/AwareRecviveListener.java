package cn.imethan.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

/**
 * 
 * RecviveListener.java
 *
 * @author Ethan Wong
 * @time 2015年11月9日下午8:25:29
 */
@Service
public class AwareRecviveListener implements ChannelAwareMessageListener  {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		
		MessageProperties messageProperties = message.getMessageProperties() ;
		
		String encoding = messageProperties.getContentEncoding();//编码
		String content = new String(message.getBody(),encoding);//内容	
		String appId = messageProperties.getAppId();//发送时设置的发送方标示appid
		
		System.out.println("[RabbitMQ] recv message -appId:"+appId +" -message:"+content);
		
		String vhost = rabbitTemplate.getConnectionFactory().getVirtualHost();//当前虚拟主机名称
		String consumerQueue = messageProperties.getConsumerQueue();//当前接收的队列名称
		
		//业务处理
		this.handle(content);
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
	
	}
}
