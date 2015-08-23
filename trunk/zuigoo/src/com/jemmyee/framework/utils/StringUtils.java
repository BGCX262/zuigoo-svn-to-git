package com.jemmyee.framework.utils;

import java.util.regex.Pattern;

/**
 * @Description: 字符串处理
 * @author:jemmyee@gmail.com
 * @date:2011-9-23
 * @version:v1.0
 */
public class StringUtils {
	
	/**
	* @Description: 过滤掉javascript脚本
	* @author:jemmyee@gmail.com
	* @date:2011-9-23
	*/
	public static String removeJavaScript(String content) {
		content = Pattern.compile("<script.*?>.*?</script>",
				Pattern.CASE_INSENSITIVE).matcher(content).replaceAll("");
		return content;
	}
    public static boolean isEmpty(CharSequence cs) {
         return cs == null || cs.length() == 0;
    }
    public static boolean isNotEmpty(CharSequence cs) {
       return !StringUtils.isEmpty(cs);
    }

	public static void main(String[] args) {

	}

}
