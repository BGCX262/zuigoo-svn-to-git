package com.jemmyee.framework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jemmyee.framework.app.ip.IPSeeker;

/**
 * @Description:IP地址位置查询
 * @author:jemmyee@gmail.com
 * @date:2010-10-25
 * @version:v1.0
 */
public class IPUtils {
	
	private static Log log= LogFactory.getLog(IPUtils.class);

	/**
	 * @Description:得到IP地址所在区域 eg:湖北省武汉市
	 * @author:jemmyee@gmail.com
	 * @date:2010-10-25
	 */
	public static String getIPLocation(String ip) {
		try {
			IPSeeker ips = new IPSeeker(ConfigUtils.getIpDataFile(),
					ConfigUtils.getIpDataPath());
			return ips.getCountry(ip)+" "+ips.getArea(ip);
		} catch (Exception e) {
			log.info("获取IP地址出错:"+e.getMessage());
			return "未知";
		}

	}
	public static void main(String[] args) {
		System.out.println(getIPLocation("58.68.229.70"));
		System.out.println(getIPLocation("61.136.243.70"));
	}
}
