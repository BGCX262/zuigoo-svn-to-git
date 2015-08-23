package com.jemmyee.framework.app.ip;

import junit.framework.TestCase;

/**
 * 测试
 * @author jemmyee
 * @date 2010-8-27
 */
public class IPTest extends TestCase {
	String dataPath = "E:/workspace/linzhen/WebRoot/data/ip";
	String dataName = "QQWry.Dat";
    String testIP="119.75.218.45";//百度
    String testIP1="58.49.29.50";//武汉热线
    String testIP4="61.136.243.70";//通山
    String testIP2="208.111.155.209";//groupon.com
	public void testIp() {
		// 指定纯真数据库的文件名，所在文件夹
		IPSeeker ip = new IPSeeker(dataName, dataPath);
		System.out.println(ip.getIPLocation(testIP4).getCountry() + ":"
				+ ip.getIPLocation(testIP4).getArea());
	}
}
