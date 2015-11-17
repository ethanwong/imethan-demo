package cn.imethan.common.utils;

import java.util.UUID;

/**
 * 生成唯一序列号的工具类.
 * 
 * @author zblink
 * @since JDK 1.7
 * @see
 */
public class GenerateSequenceUtil {

	public static String[] chars = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	public static String generateShortUuid(int randomNumberDigit) {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < randomNumberDigit; i++) {
			String str = uuid.substring(i * 2, i * 2 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 10]);
		}
		return shortBuffer.toString();
	}

	public static void main(String[] args) {
		int randomNumberDigit = 10;
		System.out.println(GenerateSequenceUtil.generateShortUuid(randomNumberDigit));
	}

}
