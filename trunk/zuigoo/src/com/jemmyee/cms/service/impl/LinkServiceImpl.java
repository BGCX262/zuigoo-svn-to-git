package com.jemmyee.cms.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.cms.dao.ILinkDao;
import com.jemmyee.cms.entity.Link;
import com.jemmyee.cms.service.ILinkService;
import com.jemmyee.framework.dao.impl.BaseDaoImpl;

@Service("linkService")
public class LinkServiceImpl extends BaseDaoImpl<Link> implements ILinkService<Link> {
	
	private static final Log log = LogFactory.getLog(LinkServiceImpl.class);

	@Autowired
	private ILinkDao linkDao;
}
