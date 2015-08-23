package com.jemmyee.framework.utils;

/**
 * @Description:得到一些常见配置参数,参数配置在com/baihong/core/config/prop/config.properties中
 * @author:jemmyee@gmail.com
 * @date:2010-10-25
 * @version:v1.0
 */
public class ConfigUtils {
	/*默认路径 */
	private final static String CLASSPATH="com/baihong/core/config/prop/config.properties";
	
	/**
	* @Description:IP库路径
	* @author:jemmyee@gmail.com
	* @date:2010-10-25
	*/
	public static String getIpDataPath(){
          return PropertiesUtils.getProperty("IP_DATA_PATH",CLASSPATH);
	}
	/**
	* @Description:IP库文件名
	* @author:jemmyee@gmail.com
	* @date:2010-10-25
	*/
	public static String getIpDataFile(){
		return PropertiesUtils.getProperty("IP_DATA_FILE",CLASSPATH);
	}

}
