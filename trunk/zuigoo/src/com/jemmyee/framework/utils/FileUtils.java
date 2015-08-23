package com.jemmyee.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 * @Description:文件处理类
 * @author:jemmyee@gmail.com
 * @date:2010-11-24
 * @version:v1.0
 */
public class FileUtils {

	/**
	* @Description:将文件复制到另外一个目录
	* @author:jemmyee@gmail.com
	* @date:2010-11-24
	*/
	public static void copyFile(String oldPath, String newPath) {
		// int length=2097152;
		try {
			File oldFile = new File(oldPath);
			File newFile = new File(newPath);
			// 如果新的目录不存在，就创建
			if (!newFile.exists()) {
				File pfile = new File(newFile.getParent());
				pfile.mkdirs();
			}
			FileInputStream in = new FileInputStream(oldFile);
			FileOutputStream out = new FileOutputStream(newFile);
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	* @Description:判断文件是否存在
	* @author:jemmyee@gmail.com
	* @date:2010-11-24
	*/
	public static boolean isFileExist(String path)
	{
		File file=new File(path);
		if(file.exists())
		{
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	* @Description:得到文件最后修改时间
	* @author:jemmyee@gmail.com
	* @date:2010-11-25
	*/
	public static Date getLastModifedTime(String path){
		File file=new File(path);
		long temp=file.getAbsoluteFile().lastModified();
		Date date=new Date(temp);
		return date;
	}
	
	
	/**
	 * @Description:得到文件大小
	 * @author:jemmyee@gmail.com
	 * @date:2010-11-25
	 */
	public static Long getFileSize(String path){
		File file=new File(path);
		long size=file.getAbsoluteFile().length();
		return size;
	}
	

	/**
	* @Description:根据文件路径读取文件
	* @author:jemmyee@gmail.com
	* @date:2010-12-7
	*/
	public static String readFileByPath(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader reader;
		StringBuffer sb = new StringBuffer();
		if (file.exists()) {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// sb.append(tempString.replaceAll(">", "&gt;").replaceAll("<",
				// "&lt;"));
				sb.append(tempString).append("\n");
				line++;
			}
			reader.close();
			// System.out.println("file:"+sb.toString());
		}
		return sb.toString();
	}
	
	/**
    * @Description:写文件
	* @author:jemmyee@gmail.com
	* @date:2011-8-15
	*/
	public static void writeFile(String path,String content)
	{
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) throws IOException {

	}
}
