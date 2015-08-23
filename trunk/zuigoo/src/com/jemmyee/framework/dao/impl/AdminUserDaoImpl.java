package com.jemmyee.framework.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.framework.dao.IAdminUserDao;
import com.jemmyee.framework.entity.AdminUser;

@Repository("adminUserDao")
public class AdminUserDaoImpl extends BaseDaoImpl<AdminUser> implements IAdminUserDao {
	
	private static final Log log = LogFactory.getLog(AdminUserDaoImpl.class);

}
