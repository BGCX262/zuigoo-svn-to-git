package com.jemmyee.framework.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.framework.dao.IAdminRoleDao;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.framework.entity.AdminRole;
import com.jemmyee.framework.service.IAdminRoleService;

@Service("adminRoleService")
public class AdminRoleServiceImpl extends BaseDaoImpl<AdminRole> implements IAdminRoleService<AdminRole>{
	
	private static final Log log = LogFactory.getLog(AdminRoleServiceImpl.class);

	@Autowired
	private IAdminRoleDao adminGroupDao;
	
}
