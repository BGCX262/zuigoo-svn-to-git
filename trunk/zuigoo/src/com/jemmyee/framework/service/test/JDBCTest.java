package com.jemmyee.framework.service.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String JDBC_STRING = "jdbc:mysql://184.164.151.5:3306/zuigoo?useUnicode=true&characterEncoding=UTF-8"; // in case of 11g use '/' instead of :
		String USER_NAME = "zuigoo";
		String PASSWD = "zuigoo840721";
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_STRING, USER_NAME, PASSWD);
			if(conn!=null)
			{
				System.out.println("connection successful!");
			}
			else{
				System.out.println("connection failure!");
				
			}
			stmt = conn.createStatement();
	 
//			String query = "SELECT * FROM TABLE TBL";
//			rs = stmt.executeQuery(query);
		}catch(SQLException sqlEx){
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null) rs.close();
				if(stmt !=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
