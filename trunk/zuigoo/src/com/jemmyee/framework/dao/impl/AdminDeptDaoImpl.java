package com.jemmyee.framework.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.framework.dao.IAdminDeptDao;
import com.jemmyee.framework.entity.AdminDept;

@Repository("adminDeptDao")
public class AdminDeptDaoImpl extends BaseDaoImpl<AdminDept> implements IAdminDeptDao {
	
	private static final Log log = LogFactory.getLog(AdminDeptDaoImpl.class);

}
