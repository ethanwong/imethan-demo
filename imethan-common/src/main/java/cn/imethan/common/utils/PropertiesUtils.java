package cn.imethan.common.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesUtils.java
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class PropertiesUtils {

	private static Properties prop = new Properties();

	/**
	 * 读取properties文件
	 * 
	 * @param filepath
	 *            文件路径
	 */
	public static void ReadProp(String filepath) {
		try {
			prop.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(filepath));
		} catch (IOException e) {
			System.out.println("*.properties文件载入失败！！！" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 根据key获取对应的value
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if (prop.getProperty(key) != null) {
			return (String) prop.getProperty(key);
		} else {
			return "";
		}

	}

}
