package com.jemmyee.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jemmyee.framework.web.Constants;

/**
 * @Description: 后台登陆认证拦截器
 * @author:jemmyee@gmail.com
 * @date:2010-10-27
 * @version:v1.0
 */
public class LogonAdminAuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	private static Log log= LogFactory.getLog(LogonAdminAuthenticationInterceptor.class);
	
	/*所有后台路径包含admin*/
	private String mappingURI="admin";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String url=request.getRequestURI().toString();
		log.debug(request.getRequestURI().toString());
		if(url.contains(mappingURI))
		{   
			if(request.getSession().getAttribute(Constants.ONLINE_ADMIN_USER)==null)
			{
				log.debug("[break into]uri:"+request.getRequestURI().toString());
//				request.setAttribute("noLogin","请先登录!");
				request.getRequestDispatcher("/entry/expired/system").forward(request, response);   
				return false;
			}
			
		}
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

}
