package cn.imethan.common.utils;

/**
 * 线程工具类
 * 
 * @author ETHAN
 * @e-mail ethanwong@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2012-7-30 上午9:43:12
 * 
 */
public class ThreadUtils {
	
	/**
	 * 线程睡眠
	 * 
	 * @param time 睡眠时间
	 * @return
	 */
	public static long sleep(long time){
		try {
			Thread.currentThread();
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("[ThreadUtils]-thread sleep error!");
			e.printStackTrace();
		}
		return time;
	}

}
