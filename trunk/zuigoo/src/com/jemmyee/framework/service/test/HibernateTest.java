package com.jemmyee.framework.service.test;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jemmyee.framework.entity.AdminLog;
import com.jemmyee.framework.service.IAdminLogService;

public class HibernateTest {

	public static void main(String[] args) {
        String[] configs = new  String[] {
				"/com/jemmyee/core/config/spring/spring-dao.xml",
				"/com/jemmyee/core/config/spring/spring-service.xml",
				"/com/jemmyee/core/config/spring/spring-datasource.xml",
				"/com/jemmyee/core/config/spring/spring-hibernate.xml"
				};
		ApplicationContext act = new ClassPathXmlApplicationContext(configs);
        IAdminLogService<AdminLog> adminLogService=(IAdminLogService<AdminLog>)act.getBean("adminLogService");
        AdminLog log=new AdminLog();
        log.setAddTime(new Date());
        log.setContent("数据测试:");
        log.setIp("127.0.0.1");
        log.setIsDelete((short)0);
        
        long start=System.currentTimeMillis();
        adminLogService.save(log);
	    long end=System.currentTimeMillis();
	     
	    System.out.println("ok");
	    System.out.println("耗时:"+(end-start)/1000);
	     
	  
	}

}
