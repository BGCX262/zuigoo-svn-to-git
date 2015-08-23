package com.jemmyee.framework.web.controller.kernel;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;

import com.jemmyee.framework.entity.AdminUser;
import com.jemmyee.framework.web.Constants;

/**
 * @Description:后台Controller基类
 * @author:jemmyee@gmail.com
 * @date:2011-4-29
 * @version:v1.0
 */
public abstract class  BaseAdminController extends BaseController implements IController {

	public AdminUser getOnlineUser(HttpServletRequest request) {
		if (request.getSession().getAttribute(Constants.ONLINE_ADMIN_USER) != null) {
			return (AdminUser) request.getSession().getAttribute(
					Constants.ONLINE_ADMIN_USER);
		} else {
			return null;
		}
	}
	 public static void main(String[] args) {
		 System.out.println(RandomUtils.JVM_RANDOM.nextDouble());
	}
	 
}
