package com.jemmyee.framework.web.utils;

public class ClassUtils {
	/**
	* @Description:得到entity的小写形式
	* @author:jemmyee@gmail.com
	* @date:2011-5-2
	*/
	public static String getEntityLowerCaseName(Class c)
	{
		return c.getSimpleName().toLowerCase();
	}
}
