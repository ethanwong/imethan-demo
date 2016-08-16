package org.imethan.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * ServerHandler.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2016年3月16日下午3:43:21
 */
public class ServerHandler extends IoHandlerAdapter {
	
	/**
	 * 接收消息
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		String msg = "<<Server received message:" + message;
		
		System.out.println(msg);

		session.write(msg);
		
		
		
//		 IoBuffer ioBuffer = (IoBuffer)message;   
//	       byte[] b = new byte[ioBuffer.limit()];   
//	       ioBuffer.get(b);  
//	     
//	  String msg = new String(b);
//
//	  System.out.println("收到客户端发来的消息为" + "  " + msg);
	  
	  //将测试消息会送给客户端
	  //session.write(str + count);
	}
	
	/**
	 * 发送消息
	 */
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		String msg = ">>Server send message：" + message.toString();
		
		System.out.println(msg);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		super.sessionOpened(session);
	}
}
