package cn.imethan.jax.rs.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * MessageInterceptor.java
 *
 * @author Ethan Wong
 * @time 2015年11月13日上午10:25:19
 */
public class MessageInterceptor extends AbstractPhaseInterceptor<Message> {

	
	public MessageInterceptor(){
		 super(Phase.RECEIVE);
	}
	public MessageInterceptor(String phase) {
		super(phase);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
//		System.out.println("############handleMessage##########");
//		System.out.println(message);
//		if (message.getDestination() != null) {
//			System.out.println(message.getId() + "#" + message.getDestination().getMessageObserver());
//		}
//		if (message.getExchange() != null) {
//			System.out.println(message.getExchange().getInMessage() + "#" + message.getExchange().getInFaultMessage());
//			System.out.println(message.getExchange().getOutMessage() + "#" + message.getExchange().getOutFaultMessage());
//		}
		
//		Exception soapExc = new Exception("阁下可能不是合法用户！");
//		throw new Fault(new Throwable("验证失败"),new QName(""));

	}

}
