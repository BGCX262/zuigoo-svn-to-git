package com.jemmyee.taoke.dao;

import java.util.List;

import com.jemmyee.framework.dao.IBaseDao;
import com.jemmyee.taoke.entity.TaokeCategory;

public interface ITaokeCategoryDao extends IBaseDao<TaokeCategory>{
	
	/**
	* @Description:查找符合条件的ID集合
	* @author:jemmyee@gmail.com
	* @date:2011-8-11
	*/
	public List<Integer> getIds(String sql);

}
