package cn.imethan.jax.rs.interceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

/**
 * CommonOutInterceptor.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2016年1月13日下午1:11:06
 */
public class CommonOutInterceptor extends AbstractPhaseInterceptor<Message> {


	public CommonOutInterceptor() {
		super(Phase.PRE_STREAM); // 触发点在流关闭之前
	}

	@Override
	public void handleMessage(Message message) throws Fault {

		try {
			OutputStream os = message.getContent(OutputStream.class);
			CachedStream cs = new CachedStream();
			message.setContent(OutputStream.class, cs);
			message.getInterceptorChain().doIntercept(message);
			CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);
			InputStream in = csnew.getInputStream();

			String result = IOUtils.toString(in, "UTF-8");
			
			/**
			 * 这里可以对result做处理，如可以对result进行加密，把密文返回给客户端 处理完后同理，写回流中
			 */
			result = Base64.encodeBase64String(result.getBytes("UTF-8"));
			IOUtils.copy(new ByteArrayInputStream(result.getBytes("UTF-8")), os);

			cs.close();
			os.flush();
			message.setContent(OutputStream.class, os);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class CachedStream extends CachedOutputStream {
		public CachedStream() {
			super();
		}

		protected void doFlush() throws IOException {
			currentStream.flush();
		}

		protected void doClose() throws IOException {
		}

		protected void onWrite() throws IOException {
		}

	}

}