package com.jemmyee.framework.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.framework.dao.IAdminLogDao;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.framework.entity.AdminLog;
import com.jemmyee.framework.service.IAdminLogService;

@Service("adminLogService")
public class AdminLogServiceImpl extends BaseDaoImpl<AdminLog> implements IAdminLogService<AdminLog> {
	
	private static final Log log = LogFactory.getLog(AdminLogServiceImpl.class);

	@Autowired
	private IAdminLogDao adminLogDao;

}
