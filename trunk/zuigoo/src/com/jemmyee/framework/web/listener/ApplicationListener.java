package com.jemmyee.framework.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jemmyee.framework.web.Constants;

/**
 * @Description: 系统全局设置
 * @author:jemmyee@gmail.com
 * @date:2010-11-15
 * @version:v1.0
 */
public class ApplicationListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		 ServletContext application = event.getServletContext();
//		 String projectPath=application.getRealPath(File.separator);
//		 String mode=ConfigXmlUtils.getConfig(projectPath+Constants.LANGUAGE_XML_CONFIG_NAME, Constants.CONFIG_LANGUAGE_DEFAULT_NAME);
		 // 全局路径
	     application.setAttribute(Constants.APPLICATION_APPROOT,application.getContextPath().equals("")?"":application.getContextPath());
	     application.setAttribute(Constants.APPLICATION_CTXROOT,"/"+application.getServletContextName());
	     //tomcat 6.0.29 application.getContextPath() =""
//	     System.out.println("***********************************:"+application.getContextPath());
//	     System.out.println("***********************************:"+application.getServletContextName());
//	     //加载语言
//	     WebUtils.initViewParam(application,mode);
	     // 系统版本号
	     application.setAttribute(Constants.APPLICATION_VERSION,application.getInitParameter(Constants.APPLICATION_VERSION));
	     
	}
	
	
	
}
