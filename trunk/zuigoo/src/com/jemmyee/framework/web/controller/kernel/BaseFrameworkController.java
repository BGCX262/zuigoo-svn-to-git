package com.jemmyee.framework.web.controller.kernel;

import org.springframework.beans.factory.annotation.Autowired;

import com.jemmyee.framework.entity.AdminDept;
import com.jemmyee.framework.entity.AdminLog;
import com.jemmyee.framework.entity.AdminRole;
import com.jemmyee.framework.entity.AdminUser;
import com.jemmyee.framework.service.IAdminDeptService;
import com.jemmyee.framework.service.IAdminLogService;
import com.jemmyee.framework.service.IAdminRoleService;
import com.jemmyee.framework.service.IAdminUserService;

public class BaseFrameworkController extends BaseAdminController {
	// service 注入
	//core
	@Autowired
	public IAdminUserService<AdminUser> adminUserService;
	@Autowired
	public IAdminRoleService<AdminRole> adminRoleService;
	@Autowired
	public IAdminLogService<AdminLog> adminLogService;
	@Autowired
	public IAdminDeptService<AdminDept> adminDeptService;

}
