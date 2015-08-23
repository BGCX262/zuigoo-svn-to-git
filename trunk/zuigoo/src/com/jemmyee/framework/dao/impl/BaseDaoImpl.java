package com.jemmyee.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jemmyee.framework.dao.IBaseDao;
import com.jemmyee.framework.dao.Page;
import com.jemmyee.framework.web.Constants;

/**
 * @Description:Dao基类实现
 * @author:jemmyee@gmail.com
 * @date:2011-3-30
 * @version:v1.0
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private static final Log log = LogFactory.getLog(BaseDaoImpl.class);

	/**
	 * @Description:该方法，防止在采用注解的情况下，会出现需要注入hibernateTemplate or
	 *                                                       SessionFactory的情况
	 * @author:jemmyee@gmail.com
	 * @date:2011-3-30
	 */
	@Autowired
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void deleteById(Serializable id) {
		String sql = "delete from " + entityClass.getName()
				+ " where id='" + id + "'";
		log.debug("delete sql:" + sql);
		getHibernateTemplate().bulkUpdate(sql);
	}

	public List<T> findAll() {
		String queryString = "from " + entityClass.getName();
		return getHibernateTemplate().find(queryString);
	}

	public T findById(Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	public List<T> findByProperty(String propertyName, Object value) {

		try {
			String queryString = "from " + entityClass.getName()
					+ " where " + propertyName + "='" + value + "' and 1=1";
			log.info(queryString);
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}

	public void update(T entity) {
		getHibernateTemplate().merge(entity);
	}

	@SuppressWarnings("deprecation")
	public Page findPageByCriteria(final DetachedCriteria detachedCriteria,
			final int pageSize, final int startIndex) {
		return (Page) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = detachedCriteria
						.getExecutableCriteria(session);
				long temp = ((Long) criteria.setProjection(
						Projections.rowCount()).uniqueResult()).longValue();
				int totalCount = (Integer.parseInt(String.valueOf(temp)));
				criteria.setProjection(null);
				List items = criteria.setFirstResult(startIndex).setMaxResults(
						pageSize).list();
				Page ps = new Page(items, totalCount, pageSize, startIndex);
				return ps;
			}
		});
	}

	public List<T> findByCriteria(DetachedCriteria criteria) {
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public void batchUpdate(String sql) throws Exception {
		SessionFactory sessionFactory = getHibernateTemplate()
				.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
		Connection conn = session.connection();
		log.debug("batch sql:" + sql);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
//		tx.commit();
	}

	public int deleteBatch(List<Serializable> ids) throws Exception {
		SessionFactory sessionFactory = getHibernateTemplate()
				.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
		String sql = "delete from " + entityClass.getName()
				+ " where id in (:ids)";
		Query query = session.createQuery(sql);
		query.setParameterList("ids", ids);
		log.debug("delete batch:" + query.getQueryString());
		int count = query.executeUpdate();
//		tx.commit();
		return count;
	}

	public void excuteSql(final String sql) throws Exception {
//		SessionFactory sessionFactory = getHibernateTemplate()
//				.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Transaction tx = session.beginTransaction();
//		Connection conn = session.connection();
//		log.debug("execute sql:" + sql);
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.executeUpdate();
//		tx.commit();
		getHibernateTemplate().execute(new HibernateCallback() {
			            public Object doInHibernate(Session session)
			                    throws HibernateException {
			                session.createQuery(sql).executeUpdate();
			                return null;
			            }
			        });

	}

	public int getCountBySQL(String sql) throws Exception {
		SessionFactory sessionFactory = getHibernateTemplate()
        .getSessionFactory();
    	Session session = sessionFactory.getCurrentSession();
    	SQLQuery query = session.createSQLQuery(sql);
    	log.debug("getcount:"+sql);
    	System.out.println("getcount:"+sql);
    	query.addScalar(Constants.HIBERNATE_SQL_ALIAS_COUNT, Hibernate.INTEGER);
    	return (Integer) query.uniqueResult();
	}
}
