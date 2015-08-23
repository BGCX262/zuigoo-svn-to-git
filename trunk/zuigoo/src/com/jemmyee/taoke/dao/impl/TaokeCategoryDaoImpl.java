package com.jemmyee.taoke.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.jemmyee.framework.dao.impl.BaseDaoImpl;
import com.jemmyee.taoke.dao.ITaokeCategoryDao;
import com.jemmyee.taoke.entity.TaokeCategory;

@Repository("taokeCategoryDao")
public class TaokeCategoryDaoImpl extends BaseDaoImpl<TaokeCategory> implements ITaokeCategoryDao {
	
	private static final Log log = LogFactory.getLog(TaokeCategoryDaoImpl.class);

	public List<Integer> getIds(String sql) {
		List<Integer> ints=new ArrayList<Integer>();
		SessionFactory sessionFactory = getHibernateTemplate()
				.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		Connection conn = session.connection();
		System.out.println("sql:"+sql);
		try {
			Statement stmt=conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				ints.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}    
		
		return ints;
	}

}
