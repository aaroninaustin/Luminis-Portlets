package edu.stedwards.luminis.portlets.r2d2.util;

import java.sql.*;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;




public class ConnectionPool {
	
	private static ConnectionPool _instance  = new ConnectionPool();
	private Properties _props;
	private ComboPooledDataSource _cpds;
	
	public static void cleanUp(Connection con){
		_instance._cleanUp(con);
		
	}
	
	public static void cleanUp(Connection con, Statement s) {
		_instance._cleanUp(con, s);
		
	}
	
	public static void cleanUp(Connection con, Statement s, ResultSet rs) {
		_instance._cleanUp(con, s, rs);
		
	}
	
	public static void destroy() throws SQLException{
		
		_instance._destroy();
	}

	public static Connection getConnection() throws SQLException {
		return _instance.getConnection();
	}
	
	public static Properties getProperties(){
		return _instance._props;
	}
	
	private void _cleanUp(Connection con){
		_cleanUp(con, null, null);
	}
	
	private void _cleanUp(Connection con, Statement s){
		_cleanUp(con ,s, null);
	}
	private void _cleanUp(Connection con, Statement s, ResultSet rs){
		try {
			if (rs != null){
				rs.close();
			}
		} catch (SQLException sqle) {
			// TODO: handle exception
			
		}
		
		try {
			if (s != null){
				s.close();
			}
		} catch ( SQLException sqle) {
			// TODO: handle exception
		}
		
		try {
			if (con != null){
				
				con.close();
			}
		} catch (SQLException sqle) {
			// TODO: handle exception
		}
		
	}
	
	private void _destroy() throws SQLException {
		
		DataSources.destroy(_cpds);
		
	}
	
	private Connection _getConnection() throws SQLException {
		
		return _cpds.getConnection();
	}
	
	private ConnectionPool(){
		try {
			//Properties
			ClassLoader classLoader = getClass().getClassLoader();
			_props = new Properties();
			
			_props.load(classLoader.getResourceAsStream("connection-pool.properties"));
			_props.list(System.out);
			
			//Pooled Data
			
			String driverClass = _props.getProperty("driver.class");
			String jdbcUrl = _props.getProperty("jdbc.url");
			String user = _props.getProperty("user");
			String password = _props.getProperty("password");
			
			int minPoolSize = 5;
			
			try {
				minPoolSize = Integer.parseInt(_props.getProperty("min.pool.size"));
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			int maxPoolSize = 5;
			
			try {
				maxPoolSize = Integer.parseInt(_props.getProperty("max.pool.size"));
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			int acpquireIncrement = 5;
			
			try {
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
