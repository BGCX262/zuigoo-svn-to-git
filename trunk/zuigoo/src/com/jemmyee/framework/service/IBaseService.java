package com.jemmyee.framework.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jemmyee.framework.dao.Page;

/**
 * @Description:所有Service的基类
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
public interface IBaseService<T> {

    public Serializable save(T entity);
	
	public void deleteById(Serializable id);
	
	public void update(T entity);

	public T findById(Serializable id);
	
	public List<T> findAll();
	
	public List<T> findByProperty(String propertyName, Object value);
	
	public List<T> findByCriteria(final DetachedCriteria criteria);
	
	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int pageSize, final int startIndex);
	/**
	* @Description:根据id批量删除
	* @author:jemmyee@gmail.com
	* @date:2011-3-30
	*/
	public int deleteBatch(List<Serializable> ids)throws Exception;
	public void excuteSql(String sql)throws Exception;
	
	/**
	* @Description:数量统计
	* @author:jemmyee@gmail.com
	* @date:2011-9-7
	*/
	public int getCountBySQL(String sql) throws Exception;
	/**
	* @Description:执行原生的批量语句
	* @author:jemmyee@gmail.com
	* @date:2010-12-14
	*/
	public void batchUpdate(String sql)throws Exception;
}
