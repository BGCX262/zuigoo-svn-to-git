package com.jemmyee.framework.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.framework.dao.IAdminRoleDao;
import com.jemmyee.framework.entity.AdminRole;

@Repository("adminRoleDao")
public class AdminRoleDaoImpl extends BaseDaoImpl<AdminRole> implements IAdminRoleDao {
	
	private static final Log log = LogFactory.getLog(AdminRoleDaoImpl.class);

}
