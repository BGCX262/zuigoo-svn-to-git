package com.jemmyee.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @Description:日期工具类
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
public class DateUtils {

	/** 日期格式化格式 */
	public static String DATE_FORMAT_YYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static String DATE_FORMAT_YYMMDD = "yyyy-MM-dd";
	
	public static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

	/**
	 * 将日期格式化为字符串形式
	 * 
	 * @auther jemmyee
	 * @date 2010-5-7
	 * @param date
	 * @return
	 */
	public static String parseDateToString(Date date,String format) {
		String str="";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					format);
			str=simpleDateFormat.format(date);
		} catch (Exception e) {
			str="";
		}
        return str;
	}

	/**
	* @Description:根据制定的格式，格式化日期
	* @author:jemmyee@gmail.com
	* @date:2010-10-15
	*/
	public static Date parseStringToDateWithFormat(String time,String format) {
		Date date = null;
		java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				format);
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
		
	}
	/**
	 * 将日期字符串转化为日期
	 * 
	 * @auther jemmyee
	 * @date 2010-5-7
	 * @param time
	 * @return
	 */
	public static Date parseStringToDate(String time) {
		Date date = null;
		java.text.SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				DATE_FORMAT_YYMMDD);
		try {
			date = simpleDateFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}
	/**
	 * desc:得到当前是一个月的那一天
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * desc:得到当前是星期几
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowWeek() {
		int temp = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		if (temp == 1) {
			return 7;
		} else {
			return temp - 1;
		}
	}

	/**
	 * desc:取得当前小时的整数，24小时制
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * desc:得到当前时间的分钟数
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowMinute() {
		return Calendar.getInstance().get(Calendar.MINUTE);

	}

	/**
	 * desc:得到当前的月份
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * desc:得到当前的年份
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * desc:得到当前的秒数
	 * 
	 * @return Feb 18, 2009
	 */
	public static int getNowSeconds() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	/**
	 * 返回指定日期的前几天
	 * 
	 * @auther jemmyee
	 * @date 2010-5-19
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 返回指定日期的后几天
	 * @auther jemmyee 
	 * @date 2010-5-19
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static void main(String[] args) {
		System.out.println(getDateAfter(new Date(),3));
	}


}
