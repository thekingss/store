package cn.itheima.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static DataSource ds=new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	
	//获取连接�?
	public static DataSource getDataSource(){
		return ds;
	}
	
	/**
	 * 从当前线程中获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = tl.get();
		//若是第一次获�? 是null
		if(conn	== null){
			conn=ds.getConnection();
			
			//将这个连接和当前线程绑定
			tl.set(conn);
		}
		
		return conn;
	}
	
	/**
	 *	�?启事�?
	 * @return 
	 * @throws SQLException 
	 */
	public static Connection beginTransaction() throws SQLException{
		//获取连接
		Connection conn = getConnection();
		
		//�?启事�?
		conn.setAutoCommit(false);
		return conn;
	}
	
	
	/**
	 * 提交事务且释放资�?
	 */
	public static void commitAndClose(){
		try {
			//获取连接
			Connection conn = getConnection();
			
			//提交事务
			if(conn != null){
				conn.commit();
			}
			
			closeConn(conn);
		} catch(Exception e){
			
		}
		
	}
	
	/**
	 * 回滚事务且释放资�?
	 */
	public static void rollbackAndClose(){
		try {
			//获取连接
			Connection conn = getConnection();
			
			//回滚事务
			if(conn != null){
				conn.rollback();
			}
			
			closeConn(conn);
			
		} catch (SQLException e) {
			//
		}
	}
	
	/**
	 * 释放资源 且和当前线程解绑
	 * @param conn
	 */
	private static void  closeConn(Connection conn){
		try {
			//释放资源
			if(conn != null){
				conn.close();
			}
			//将线程和连接解绑
			tl.remove();
		} catch (Exception e) {
		}
		
		conn = null;
	}
}
