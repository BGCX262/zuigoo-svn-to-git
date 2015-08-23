package com.jemmyee.taoke.service;

import java.util.List;

import com.jemmyee.framework.service.IBaseService;


public interface ITaokeCategoryService<TaokeCategory> extends IBaseService<TaokeCategory> {
	/**
	* @Description:查找符合条件的ID集合
	* @author:jemmyee@gmail.com
	* @date:2011-8-11
	*/
	public List<Integer> getIds(String sql);
}
