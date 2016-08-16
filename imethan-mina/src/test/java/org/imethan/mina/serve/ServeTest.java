package org.imethan.mina.serve;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.imethan.mina.BeanTest;
import org.imethan.mina.CodecFilter;
import org.imethan.mina.ServerHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ServeTest.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2016年3月16日下午4:06:01
 */
public class ServeTest {
	
	private static final int PORT = 9123;
	

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("services.xml");
		BeanTest beanTest = ctx.getBean(BeanTest.class);
		System.out.println(beanTest.getName());

//		 ServeTest serveTest = new ServeTest();
//		 serveTest.initialize();
		
		
//		IoAcceptor acceptor = new NioSocketAcceptor();
//
//        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
//        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
//
//        acceptor.setHandler(new ServerHandler());
//        acceptor.getSessionConfig().setReadBufferSize( 2048 );
//        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
//        acceptor.bind( new InetSocketAddress(PORT) );
	}

	public void initialize() throws IOException {

		// 创建一个 Acceptor
		NioDatagramAcceptor acceptor = new NioDatagramAcceptor();

		// 加入 Handler
		acceptor.setHandler(new ServerHandler());

		acceptor.getFilterChain().addLast("logging", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CodecFilter()));

		// 创建 Session Configuration
		DatagramSessionConfig dcfg = acceptor.getSessionConfig();
		dcfg.setReuseAddress(true);
		System.out.println("Starting Server......");
		// 绑定并监听
		acceptor.bind(new InetSocketAddress(9999));
		System.out.println("Server listening on 9999");
	}

}
