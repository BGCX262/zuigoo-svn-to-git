package com.jemmyee.taoke.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.taoke.dao.ITaokeItemDao;
import com.jemmyee.taoke.entity.TaokeItem;
import com.jemmyee.taoke.service.ITaokeItemService;

@Service("taokeItemService")
public class TaokeItemServiceImpl extends BaseDaoImpl<TaokeItem> implements ITaokeItemService<TaokeItem>{
	
	private static final Log log = LogFactory.getLog(TaokeItemServiceImpl.class);
	@Autowired
	private ITaokeItemDao taokeItemDao;
}
