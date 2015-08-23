package com.jemmyee.framework.web.controller.kernel;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:所有Controller接口
 * @author:jemmyee@gmail.com
 * @date:2011-4-29
 * @version:v1.0
 */
public interface IController {

	/**
	* @Description:得到当前user  前台controller得到user 后台controller得到adminuser  无返回null
	* @author:jemmyee@gmail.com
	* @date:2011-8-31
	*/
	public Object getOnlineUser(HttpServletRequest request);
}
