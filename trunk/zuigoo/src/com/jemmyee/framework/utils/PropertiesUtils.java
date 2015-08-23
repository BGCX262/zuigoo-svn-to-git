package com.jemmyee.framework.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesUtils {
	/*默认路径 */
	private final static String CLASSPATH="com/baihong/core/config/prop/config.properties";

	/**
	 * 功能:测试
	 * @param args
	 * @date Oct 27, 2008
	 * @time 2:27:29 PM
	 */
	public static void main(String[] args) {
		System.out.println(getProperty("IP_DATA_PATH"));

	}	
	/**
	 * 功能:取得指定路径的资源文件
	 * @param propertyName
	 * @param path
	 * @return
	 * @date Oct 27, 2008
	 * @time 2:33:18 PM
	 */
	public static String getProperty(String propertyName,String path){
		Resource res = new ClassPathResource(path);
		try {
			Properties prop=PropertiesLoaderUtils .loadProperties(res);
			return prop.getProperty(propertyName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 功能:取得资源路径文件
	 * @param propertyName
	 * @return
	 * @date Oct 27, 2008
	 * @time 2:34:07 PM
	 */
	public static String getProperty(String propertyName){
		Resource res = new ClassPathResource(CLASSPATH);
		try {
			Properties prop=PropertiesLoaderUtils .loadProperties(res);
			return prop.getProperty(propertyName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
