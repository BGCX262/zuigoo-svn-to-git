package com.jemmyee.taoke.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.taoke.dao.ITaokeItemDao;
import com.jemmyee.taoke.entity.TaokeItem;

@Repository("taokeItemDao")
public class TaokeItemDaoImpl extends BaseDaoImpl<TaokeItem> implements ITaokeItemDao {
	
	private static final Log log = LogFactory.getLog(TaokeItemDaoImpl.class);

}
