package com.jemmyee.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;


/**
 * @Description:html页面处理
 * @author:jemmyee@gmail.com
 * @date:2011-8-16
 * @version:v1.0
 */
public class HTMLGeneratorUtils extends HttpServlet{

	/**
	 * 得到URL的内容
	 * @auther jemmyee 
	 * @date 2010-7-24
	 * @param url
	 * @return
	 */
	public static final String getURLContent(final String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		Pattern pattern = Pattern
				.compile("(http://|https://){1}[\\w\\.\\-/:]+");
		Matcher matcher = pattern.matcher(url);
		if (!matcher.find()) {
			return null;
		}
		StringBuffer sb = new StringBuffer();

		try {
			URL _url = new URL(url);
			URLConnection urlConnection = _url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream(),"UTF-8"));//指定编码格式，否则会出现乱码

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	
	 /**
	 * 生成静态页面
	 * @auther jemmyee 
	 * @date 2010-7-24
	 * @param request
	 * @param url
	 * @param toWebRoot
	 * @param encoding
	 * @throws IOException
	 */
	public static void generateHtml(HttpServletRequest request, String url,String cPath,String htmlFile, String encoding) throws IOException {  
		   
		     if (null == url) {  
		         url = request.getRequestURL().toString();  
		     }  
		     String ctxPath =cPath;
		     if (!ctxPath.endsWith(File.separator)) {  
		         ctxPath += File.separator;  
		     }  
		     String pre=htmlFile.substring(0,htmlFile.length()-38);
		     File dir=new File(ctxPath+pre);
		     if(!dir.exists()){
		    	 dir.mkdirs();
		     }
		     File file=new File(ctxPath+htmlFile);
		     FileUtils.writeStringToFile(file, HTMLGeneratorUtils.getURLContent(url), encoding); 
		 }  
	

	 /**
	 * 生成静态页面
	 * @auther jemmyee 
	 * @date 2010-7-24
	 * @param request
	 * @param url
	 * @param toWebRoot
	 * @param encoding
	 * @throws IOException
	 */
	public static void generateItemHtml(HttpServletRequest request, String url,String cPath,String htmlPath, boolean toWebRoot, String encoding) throws IOException {  
		   
		     if (null == url) {  
		         url = request.getRequestURL().toString();  
		     }  
		   
		     String contextPath = request.getContextPath();  
		     String seq = StringUtils.substring(String.valueOf(new Date().getTime()), -6);  
		   
		     String ctxPath =cPath;
		     if (!ctxPath.endsWith(File.separator)) {  
		         ctxPath += File.separator;  
		     }  
		   
		     String filePath = StringUtils.substringAfter(url, contextPath);  
		     filePath = filePath.replaceAll("\\.(do|jsp|html|shtml)$", ".html");  
		     File file=null;
		     String savePath = "";  
		     String autoCreatedDateDir = "";  
		     if (!toWebRoot) {  
		         savePath = StringUtils.join(new String[] { "files", "history", "" }, File.separator);  
		   
		         String[] folderPatterns = new String[] { "yyyy", "MM", "dd", "" };  
		         autoCreatedDateDir = DateFormatUtils.format(new Date(), StringUtils.join(folderPatterns, File.separator));  
		   
		         filePath = StringUtils.substringBefore(filePath, ".html") + "-" + seq + ".html";  
		         file= new File(ctxPath + savePath + autoCreatedDateDir + filePath); 
		     }  
		     if(htmlPath!=null){
		    	 file = new File(ctxPath + htmlPath);  
		     }
		     
		     FileUtils.writeStringToFile(file, HTMLGeneratorUtils.getURLContent(url), encoding); 
		 }  
	
	public static void main(String[] args) throws IOException {
		String src = HTMLGeneratorUtils.getURLContent("http://www.taobao.com/");
		System.out.println(src);
		File file = new File("D:" + File.separator + "index.html");
		FileUtils.writeStringToFile(file, src, "UTF-8");
	}
}
