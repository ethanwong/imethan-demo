package cn.imethan.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author ETHAN
 * @copyright 2011-2015
 * @version 1.0
 *
 */
public class DateUtils {

	public static Calendar calendar = Calendar.getInstance();

	/**
	 * 日期格式yyyy-MM-dd HH:mm:ss
	 */
	public static String DATE_PATTERN_01 = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式yyyy年MM月dd日 HH:mm:ss
	 */
	public static String DATE_PATTERN_02 = "yyyy年MM月dd日 HH:mm:ss";

	/**
	 * 日期格式yyyy-MM-dd
	 */
	public static String DATE_PATTERN_03 = "yyyy-MM-dd";

	/**
	 * 日期格式yyyy年MM月dd日
	 */
	public static String DATE_PATTERN_04 = "yyyy年MM月dd日";

	/**
	 * 日期格式MM月dd日
	 */
	public static String DATE_PATTERN_05 = "MM月dd日";

	/**
	 * 日期格式yyyy-MM-dd-HH-mm
	 */
	public static String DATE_PATTERN_06 = "yyyy-MM-dd-HH-mm";

	/**
	 * 日期格式yyyy-MM
	 */
	public static String DATE_PATTERN_07 = "yyyy-MM";

	/**
	 * 日期格式yyyyMMdd
	 */
	public static String DATE_PATTERN_08 = "yyyyMMdd";

	/**
	 * 日期格式yyyyMMddHHmmsss
	 */
	public static String DATE_PATTERN_09 = "yyyyMMddHHmmsss";

	/**
	 * 日期格式yyMMdd
	 */
	public static String DATE_PATTERN_10 = "yyMMdd";

	/**
	 * 日期格式yyyy-MM-dd HH
	 */
	public static String DATE_PATTERN_11 = "yyyy-MM-dd HH";

	/**
	 * 获取当前年
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月4日 下午8:03:40
	 */
	public static int getYear() {
		return DateUtils.calendar.get(Calendar.YEAR);
	}

	/**
	 * 格式化时间格式
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static Date StringToDate(String source, String form) {
		Date date = null;
		if (source != null && !source.trim().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			try {
				date = sdf.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	/**
	 * 格式化时间格式
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static String DateToString(Date source, String form) {
		String resultDate = "";
		if (source != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			resultDate = sdf.format(source);
		}
		return resultDate;
	}

	/**
	 * 转换时间格式
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static Date DateToDate(Date source, String form) {
		Date date = null;
		if (source != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			String stringDate = sdf.format(source);
			try {
				date = sdf.parse(stringDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	/**
	 * 格式化String时间
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static String stringToString(String source, String form) {
		String stringDate = "";
		if (source != null && !source.trim().equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat(form);
			Date date = null;
			;
			try {
				date = sdf.parse(source);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stringDate = sdf.format(date);
		}

		return stringDate;
	}

	/**
	 * 获取当前时间
	 * 
	 * @param form
	 * @return
	 */
	public static String getDatetimeStr(String form) {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(form);
		return sdf.format(date);
	}

	/**
	 * 时间戳 格式化
	 * 
	 * @param source
	 * @param form
	 * @return
	 */
	public static String formatLongDate(Long source, String form) {
		SimpleDateFormat sdf = new SimpleDateFormat(form);
		return sdf.format(source);
	}

	/**
	 * 获取当前时间前的时间点
	 * 
	 * @param time
	 *            时间间隔
	 * @return
	 */
	public static String getBeforeNowDateTime(Long time) {
		long now = new Date().getTime();
		long result = now - time;
		String resultDate = DateUtils.formatLongDate(result, DateUtils.DATE_PATTERN_01);

		return resultDate;
	}

	/**
	 * 获取日期,例如20111001,格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDateStr() {
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		return year + month + day;
	}

	/**
	 * 获取时间，例如133040，格式：HHmmss
	 * 
	 * @return
	 */
	public static String getTimeStr() {
		String HH = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String mm = String.valueOf(calendar.get(Calendar.MINUTE));
		String ss = String.valueOf(calendar.get(Calendar.SECOND));
		return HH + mm + ss;
	}

	/**
	 * 获取当前LONG格式的时间
	 * 
	 * @return
	 */
	public static Long getLongTime() {
		return new Date().getTime();
	}

	public static Long getLongTime(String pattern) {
		return DateToDate(new Date(), pattern).getTime() / 1000;
	}

	/**
	 * 获取间隔时间日期
	 * 
	 * @param monthLag
	 *            “-”：向前，“+”：向后
	 * @return
	 */
	public static String getLagMonthTime(int monthLag) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, monthLag);
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN_07);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取间隔时间日期
	 * 
	 * @param dayLag
	 *            “-”：向前，“+”：向后
	 * @return
	 */
	public static String getLagDateTime(int dayLag) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, dayLag);
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN_01);
		return sdf.format(c.getTime());
	}

	/**
	 * 获取间隔时间日期
	 * 
	 * @param dayLag
	 *            “-”：向前，“+”：向后
	 * @return
	 */
	public static Date getDateTime(int dayLag) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, dayLag);
		return c.getTime();
	}

	/**
	 * 获取指定时间的间隔日期
	 * 
	 * @param date
	 *            指定的时间
	 * @param dayLag
	 *            “-”：向前，“+”：向后
	 * @return
	 */
	public static Date getDateTime(Date date, int dayLag) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + dayLag);
		return now.getTime();
	}

	/**
	 * 获取指定时间点的几个小时后时间
	 * 
	 * @param date
	 * @param hourLag
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月18日 下午4:12:13
	 */
	public static Date getDateTimeByPlusHour(Date date, int hourLag) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hourLag);
		return calendar.getTime();
	}

	/**
	 * 方法的作用:判断当前日期是星期几.<br/>
	 * 方法适用条件:这里描述这个方法适用条件–可选.<br/>
	 * 执行流程:这里描述这个方法的执行流程–可选.<br/>
	 * 使用方法:这里描述这个方法的使用方法 –可选.<br/>
	 * 注意事项:这里描述这个方法的注意事项 –可选.<br/>
	 * 
	 * @param pTime
	 *            需要判断的时间
	 * @return
	 * @throws Exception
	 * 
	 * @author txq
	 * @create-time 2014-11-4 上午9:08:07
	 */
	public static int getDayForWeek(Calendar c) {
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 获取时间是星期几
	 * 
	 * @param date
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月5日 上午8:53:14
	 */
	public static int getDayForWeek(Date date) {
		calendar.setTime(date);
		int dayForWeek = 0;
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	/**
	 * 判断时间是否是周末
	 * 
	 * @param date
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月5日 上午8:56:23
	 */
	public static boolean isWeekend(Date date) {
		int day = DateUtils.getDayForWeek(date);
		if (day == 6 || day == 7) {
			return true;
		}
		return false;
	}

	/**
	 * 当年的开始时间
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月4日 下午8:09:37
	 */
	public static Date getCurrentYearStartTime() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		String source = String.valueOf(year) + "-01-01 00:00:00";
		return DateUtils.StringToDate(source, DateUtils.DATE_PATTERN_01);
	}

	/**
	 * 当年的结束时间
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月4日 下午8:09:41
	 */
	public static Date getCurrentYearEndTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentYearStartTime());
		calendar.add(Calendar.YEAR, 1);
		return calendar.getTime();
	}

	/**
	 * 获取两个时间间隔的天数
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月4日 下午8:24:49
	 */
	public static int getDayBetweenTwoDate(Date beginDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(beginDate);
		c2.setTime(endDate);

		int betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR) + 1;

		return betweenDays;
	}

	/**
	 * 获取当年的天数
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月4日 下午8:34:46
	 */
	public static int getYearDayCount() {
		Date beginDate = DateUtils.getCurrentYearStartTime();
		Date endDate = DateUtils.getCurrentYearEndTime();

		// 两个日期相差的天数
		Long time = endDate.getTime() - beginDate.getTime();
		Long day = time / 3600000 / 24 + 1;

		return day.intValue();
	}

	/**
	 * 获取截止今天当年剩余的天数
	 * 
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月4日 下午8:34:46
	 */
	public static int getYearDayCountAsOfToday() {
		Date beginDate = new Date();
		Date endDate = DateUtils.getCurrentYearEndTime();
		// 两个日期相差的天数
		Long time = endDate.getTime() - beginDate.getTime();
		Long day = time / 3600000 / 24 + 1;

		return day.intValue();
	}

	/**
	 * 方法的作用:取得指定日期的时间戳.<br/>
	 * 方法适用条件:.<br/>
	 * 使用方法:.<br/>
	 * 注意事项:.<br/>
	 * 
	 * @param date
	 * @return
	 * 
	 * @author cyd
	 * @create-time 2015年5月8日 下午1:40:42
	 */
	public static long getTimeStamp(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtils.StringToDate(date, DateUtils.DATE_PATTERN_01));
		return cal.getTimeInMillis();
	}

	/**
	 * 方法的作用:获取时间戳.<br/>
	 * 方法适用条件:.<br/>
	 * 使用方法:.<br/>
	 * 注意事项:.<br/>
	 * 
	 * @param date
	 * @return
	 * 
	 * @author txq
	 * @create-time 2015年5月9日 上午9:17:17
	 */
	public static long getTimeStamp(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTimeInMillis();
	}

	/**
	 * 根据开始时间和结束时间获取时间列表
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月8日 上午10:23:43
	 */
	public static List<String> getDateTimeListBetweenTwoDate(Date beginDate, Date endDate) {
		List<String> list = new ArrayList<String>();
		int day = DateUtils.getDayBetweenTwoDate(beginDate, endDate);// 获取天数
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		for (int i = 0; i < day; i++) {

			String format = "";
			// 如果是第一天，则保留时间时间精度
			if (i == 0) {
				format = DateUtils.DATE_PATTERN_01;
			} else {
				format = DateUtils.DATE_PATTERN_03;
			}
			String date = DateUtils.DateToString(DateUtils.DateToDate(calendar.getTime(), format), DateUtils.DATE_PATTERN_01);
			list.add(date);
			calendar.add(Calendar.DATE, 1); // 往后加1天
		}
		return list;
	}

	/**
	 * 根据开始时间和结束时间获取时间戳列表
	 * 
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 *
	 * @author Ethan
	 * @create-time 2015年5月8日 上午10:23:43
	 */
	public static List<String> getDateTimestampListBetweenTwoDate(Date beginDate, Date endDate) {
		List<String> list = new ArrayList<String>();
		int day = DateUtils.getDayBetweenTwoDate(beginDate, endDate);// 获取天数
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		for (int i = 0; i < day; i++) {
			String format = "";
			// 如果是第一天，则保留时间时间精度
			if (i == 0) {
				format = DateUtils.DATE_PATTERN_01;
			} else {
				format = DateUtils.DATE_PATTERN_03;
			}
			Date dateTemp = DateUtils.DateToDate(calendar.getTime(), format);
			String date = String.valueOf(dateTemp.getTime());
			list.add(date);
			calendar.add(Calendar.DATE, 1); // 往后加1天
		}
		return list;
	}

	public static void main(String args[]) {
		// String nowDate = DateUtils.getDatetimeStr(DateUtils.DATE_PATTERN_01);
		// String targetDate = DateUtils.getLagDateTime(-2);
		// System.out.println("nowDate:"+nowDate);
		// System.out.println("targetDate:"+targetDate);
		System.out.println(DateUtils.getYearDayCountAsOfToday());
		System.out.println(DateUtils.getYearDayCount());

		Date beginDate = DateUtils.getCurrentYearStartTime();
		Date endDate = DateUtils.getCurrentYearEndTime();

		System.out.println("beginDate:" + beginDate);
		System.out.println("endDate:" + endDate);
	}
}
