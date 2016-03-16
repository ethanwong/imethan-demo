package org.imethan.mina;

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
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		// super.messageReceived(session, message);
		System.out.println("message :" + message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {

		System.out.println("发送的消息是：" + message.toString());
		// super.messageSent(session, message);
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
