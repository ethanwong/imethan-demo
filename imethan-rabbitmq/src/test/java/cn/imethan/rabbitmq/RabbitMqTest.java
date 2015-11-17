package cn.imethan.rabbitmq;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.imethan.rabbitmq.sender.RabbitMqSender;

/**
 * 
 * RabbitMqTest.java
 *
 * @author Ethan Wong
 * @time 2015年11月9日下午8:32:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rabbitmq/applicationContext-rabbitmq.xml"}) 
public class RabbitMqTest{  
	
	@Autowired
	private RabbitMqSender rabbitMqSender;
	
	@BeforeClass
	public static void initRabbitMq(){

	}
	
	/**
	 * 测试向MQ发送消息
	 *
	 * @author Ethan Wong
	 * @create-time 2015年5月6日 下午2:47:34
	 */
	@Test
	public void testSend(){
		rabbitMqSender.sendMessage("defaultqueue", "向RabbitMQ发送消息！！！");
	}
	
	@AfterClass
	public static void testRecv(){
		
	}
	
}
