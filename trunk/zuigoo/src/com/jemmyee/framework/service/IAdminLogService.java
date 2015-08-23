package com.jemmyee.framework.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;


public interface IAdminLogService<AdminLog> extends IBaseService<AdminLog> {
	public List<AdminLog> findByCriteria(final DetachedCriteria criteria);

}
