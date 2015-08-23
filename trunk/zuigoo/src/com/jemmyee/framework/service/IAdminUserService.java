package com.jemmyee.framework.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;


public interface IAdminUserService<AdminUser> extends IBaseService<AdminUser> {
	public List<AdminUser> findByCriteria(final DetachedCriteria criteria);

}
