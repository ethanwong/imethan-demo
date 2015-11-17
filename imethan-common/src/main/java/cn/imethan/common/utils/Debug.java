package cn.imethan.common.utils;

/**
 * 控制台打印调试类
 * 
 * @author Ethan
 * @since JDK 1.7
 * @see
 */
public class Debug {

	public static Boolean isDebugPrint = false;

	static {
		PropertiesUtils.ReadProp("main/init.properties");
		String debug = PropertiesUtils.getValue("zblink.debug");
		isDebugPrint = debug.equals("true") ? true : false;
	}

	/**
	 * 打印调试信息
	 * 
	 * @param debugType
	 * @param msg
	 */
	public static void println(String msg) {
		if (isDebugPrint) {
			System.out.println(msg);
		}
	}
}