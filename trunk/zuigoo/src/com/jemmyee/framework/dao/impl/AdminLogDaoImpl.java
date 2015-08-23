package com.jemmyee.framework.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.framework.dao.IAdminLogDao;
import com.jemmyee.framework.entity.AdminLog;

@Repository("adminLogDao")
public class AdminLogDaoImpl extends BaseDaoImpl<AdminLog> implements IAdminLogDao {
	
	private static final Log log = LogFactory.getLog(AdminLogDaoImpl.class);

}
