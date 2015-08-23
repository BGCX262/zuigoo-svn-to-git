package com.jemmyee.framework.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.framework.dao.IAdminDeptDao;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.framework.entity.AdminDept;
import com.jemmyee.framework.service.IAdminDeptService;

@Service("adminDeptService")
public class AdminDeptServiceImpl extends BaseDaoImpl<AdminDept> implements IAdminDeptService<AdminDept>{
	
	private static final Log log = LogFactory.getLog(AdminDeptServiceImpl.class);

	@Autowired
	private IAdminDeptDao adminDeptDao;
	
}
