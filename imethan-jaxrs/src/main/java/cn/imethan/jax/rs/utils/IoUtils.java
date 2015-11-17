package cn.imethan.jax.rs.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * IoUtils.java
 *
 * @author Ethan Wong
 * @since JDK 1.7
 * @datetime 2015年11月16日上午10:37:00
 */
public class IoUtils {
	
	/**
	 * 从InputStream中读取数据
	 * @param inputStream
	 * @return
	 * @throws IOException
	 *
	 * @author Ethan Wong
	 * @datetime 2015年11月16日上午10:40:19
	 */
	public static String getStringFromInputStream(InputStream inputStream) throws IOException{
		String result = "";
		if(inputStream !=null){
			int byteCount = inputStream.available();
			byte[] bytes = new byte[byteCount];
			int readCount = 0; // 已经成功读取的字节的个数
			while (readCount < byteCount) {
				readCount += inputStream.read(bytes, readCount, byteCount - readCount);
			}
			result = new String(bytes);
		}
		return result;
	}

}
