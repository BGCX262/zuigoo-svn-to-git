package com.jemmyee.cms.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.cms.dao.ILinkDao;
import com.jemmyee.cms.entity.Link;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;

@Repository("linkDao")
public class LinkDaoImpl extends BaseDaoImpl<Link> implements ILinkDao {
	
	private static final Log log = LogFactory.getLog(LinkDaoImpl.class);

}
