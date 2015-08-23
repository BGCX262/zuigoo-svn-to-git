package com.jemmyee.framework.utils;

import com.jemmyee.framework.web.Constants;

/**
 * @Description:数据库操作相关的工具类
 * @author:jemmyee@gmail.com
 * @date:2011-3-30
 * @version:v1.0
 */
public class DBUtils {
	
	/**
	* @Description:根据实体名得到表名
	* @author:jemmyee@gmail.com
	* @date:2011-3-30
	*/
	public static String getTableName(String entityName)
	{
		StringBuffer sb=new StringBuffer();
		int i=0;
		Character chUpper=null;//第二个大写字母作为分隔符
		for(char ch:entityName.toCharArray())
		{
             if(Character.isUpperCase(ch))
             {
            	 if(i==0)
            	 {
            		 ++i;
            		 continue;
            	 }
            	 else{
            		 chUpper=ch;
            		 break;
            	 }
             }
             
		}
		String temp[]=entityName.split(chUpper.toString());
		sb.append(Constants.DB_TABLE_PREFIX);
		sb.append(temp[0].toLowerCase()).append("_").append(chUpper.toString().toLowerCase()).append(temp[1].toLowerCase());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String name="GroupAdmin";
        System.out.println(getTableName(name));
	}

}
