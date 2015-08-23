package com.jemmyee.framework.utils;

import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @Description:序列号生成
 * @author:jemmyee@gmail.com
 * @date:2011-9-1
 * @version:v1.0
 */
public class SNUtils {
	
	public static void main(String[] args) {
		System.out.println(get16NumberSNWithDate());
	}

	/**
	* @Description:得到序列号 包含日期  长度 16  eg:2011090154293651
	* @author:jemmyee@gmail.com
	* @date:2011-9-1
	*/
	public static String get16NumberSNWithDate()
	{
		StringBuffer sb=new StringBuffer();
		sb.append(DateUtils.parseDateToString(new Date(),DateUtils.DATE_FORMAT_YYYYMMDD)).append(RandomStringUtils.randomNumeric(8));
		return sb.toString();
	}
}
