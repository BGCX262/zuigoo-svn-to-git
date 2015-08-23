package com.jemmyee.framework.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;


/**
 * @Description:Dao基类接口
 * @author:jemmyee@gmail.com
 * @date:2011-3-30
 * @version:v1.0
 */
public interface IBaseDao<T> {
	
	public Serializable save(T entity);
	
	public void deleteById(Serializable id);
	
	public void update(T entity);

	public T findById(Serializable id);
	
	public List<T> findByProperty(String propertyName, Object value);
	
	public List<T> findAll();

	/**
	* @Description: 条件查询，不分页
	* @author:jemmyee@gmail.com
	* @date:2010-11-8
	*/
	public List<T> findByCriteria(final DetachedCriteria criteria);
	
	/**
	 * 分页查询
	 * @param detachedCriteria
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int pageSize, final int startIndex);
	

	/**
	* @Description:根据id批量删除
	* @author:jemmyee@gmail.com
	* @date:2011-3-30
	*/
	public int deleteBatch(List<Serializable> ids)throws Exception;
	

	/**
	* @Description:执行原生的sql
	* @author:jemmyee@gmail.com
	* @date:2011-4-29
	*/
	public void excuteSql(String sql)throws Exception;
	
	
	/**
	* @Description:数量统计 包括count  max  sun  min等  select ..  as name  name为固定名字
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
