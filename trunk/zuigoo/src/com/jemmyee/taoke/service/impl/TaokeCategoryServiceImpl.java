package com.jemmyee.taoke.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.taoke.dao.ITaokeCategoryDao;
import com.jemmyee.taoke.entity.TaokeCategory;
import com.jemmyee.taoke.service.ITaokeCategoryService;

@Service("taokeCategoryService")
public class TaokeCategoryServiceImpl extends BaseDaoImpl<TaokeCategory> implements ITaokeCategoryService<TaokeCategory> {
	
	private static final Log log = LogFactory.getLog(TaokeCategoryServiceImpl.class);

	@Autowired
	private ITaokeCategoryDao taokeCategoryDao;

	public List<Integer> getIds(String sql) {
		return taokeCategoryDao.getIds(sql);
	}
	
}
