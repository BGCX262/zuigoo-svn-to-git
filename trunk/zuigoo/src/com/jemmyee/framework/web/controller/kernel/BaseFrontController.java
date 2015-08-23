package com.jemmyee.framework.web.controller.kernel;

import javax.servlet.http.HttpServletRequest;

import com.jemmyee.framework.web.Constants;
import com.taobao.api.domain.User;

/**
 * @Description:前台Controller基类
 * @author:jemmyee@gmail.com
 * @date:2011-4-29
 * @version:v1.0
 */
public class BaseFrontController  extends BaseController implements IController {

	public User getOnlineUser(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constants.ONLINE_USER) != null) {
			return (User) request.getSession().getAttribute(
					Constants.ONLINE_USER);
		} else {
			return null;
		}
	}

}
