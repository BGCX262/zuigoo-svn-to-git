package com.jemmyee.framework.web.controller.kernel;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.entity.AdminLog;
import com.jemmyee.framework.service.IAdminLogService;
import com.jemmyee.framework.web.Constants;

/**
 * @Description:所有controller的基类
 * @author:jemmyee@gmail.com
 * @date:2011-3-31
 * @version:v1.0
 */
public class BaseController{
	
	@Autowired
	public IAdminLogService<AdminLog> adminLogService;
	
	/**
	* @Description:得到访问IP 解决配置了Apache,Squid等反向代理软件中无法获取真实IP的情况
	* @author:jemmyee@gmail.com
	* @date:2011-9-1
	*/
	public String getIP(HttpServletRequest request)
	{
	        String ip = request.getHeader("x-forwarded-for");
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getHeader("WL-Proxy-Client-IP");
	        }
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	            ip = request.getRemoteAddr();
	        }
	        return ip;
	}


	/**
	* @Description:返回系统的根网址 eg:http://localhost:8080/secs/
	* @author:jemmyee@gmail.com
	* @date:2011-9-1
	*/
	public String getWebRoot(HttpServletRequest request) {
		String app = request.getContextPath();// 项目名称 eg:secs
		String webRoot = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + app + "/";
		return webRoot;

	}


	/**
	* @Description:得到项目绝对地址 eg:E:\tomcat-6.0.20\webapps\secs\
	* @author:jemmyee@gmail.com
	* @date:2011-9-1
	*/
	public String getAppRoot(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath(
				File.separator);
	}


	/**
	* @Description:得到webapps的路径
	* @author:jemmyee@gmail.com
	* @date:2011-9-1
	*/
	public String getWebappsPath(HttpServletRequest request) {
		// E:\tomcat-6.0.20\webapps\secs
		String path = getAppRoot(request);
		String temps[] = path.split("webapps");
		String webapps = temps[0];
		return webapps + "/webapps";

	}

	public String getTomcatPath(HttpServletRequest request) {
		String path = request.getRealPath("");
		String temps[] = path.split("webapps");
		String tomcat = temps[0];
		return tomcat;

	}
	public Object getSessionValue(String name,HttpServletRequest request)
	{
		Object obj=request.getSession().getAttribute(name);
        return obj;
	}
	public void initToConfig(ModelAndView mv,String ROOT_URI,String ROOT_VIEW) {
		 mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+Constants.MV_VIEW_CONFIG);
	}
	public void initToAdd(ModelAndView mv,String ROOT_URI,String ROOT_VIEW) {
		 mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+Constants.MV_VIEW_ADD);
	}
	public void initToEdit(ModelAndView mv,Object item,String ROOT_URI,String ROOT_VIEW) {
		   mv.addObject(Constants.MV_OBJECT_ITEM,item);
		   mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		   mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+Constants.MV_VIEW_EDIT);
	}
	public void initDetail(ModelAndView mv,Object item,String ROOT_URI,String ROOT_VIEW) {
		mv.addObject(Constants.MV_OBJECT_ITEM,item);
	  mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+Constants.MV_VIEW_DETAIL);
	}
	public void initList(ModelAndView mv,Page page,String listView,String pageURI,String ROOT_URI,Object bean,String ROOT_VIEW,Integer tabID,Integer pageSize,Integer startIndex) {
		mv.addObject(Constants.MV_OBJECT_PAGE, page);
		mv.addObject(Constants.MV_OBJECT_BEAN, bean);
		 mv.addObject(Constants.MV_OBJECT_ROOT_URI,ROOT_URI);
		mv.addObject("tabID",tabID);
		mv.addObject("pageSize",pageSize);
		mv.addObject("startIndex",startIndex);
		mv.addObject(Constants.MV_OBJECT_PAGE_URI,pageURI);
		mv.setViewName(ROOT_VIEW+Constants.SYM_PATH_SEPARATOR+listView);
	}

	/**
	* @Description:记录操作日志
	* @author:jemmyee@gmail.com
	* @date:2011-9-4
	*/
	public void initLog(String user,String content,HttpServletRequest request){
		AdminLog log=new AdminLog();
		log.setAddTime(new Date());
		log.setContent(content);
		log.setIp(request.getRemoteAddr());
		log.setIsDelete((short)0);
		log.setUserName(user);
		log.setType((short)1);
		adminLogService.save(log);
	}
	
}
