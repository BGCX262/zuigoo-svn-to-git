package com.jemmyee.framework.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.framework.dao.IAdminUserDao;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.framework.entity.AdminUser;
import com.jemmyee.framework.service.IAdminUserService;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseDaoImpl<AdminUser> implements IAdminUserService<AdminUser> {
	
	private static final Log log = LogFactory.getLog(AdminUserServiceImpl.class);

	@Autowired
	private IAdminUserDao adminUserDao;

}
