package dbUtils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库工具类
 * @author 毛燕丰
 * @caeateTime 2019年4月10日上午8:43:06
   @package_name dbUtils
	@file_name DbUtils.java
 */

public class MyDbUtils {
	//创建c3p0数据库连接池对象
	private static ComboPooledDataSource dataSource= new ComboPooledDataSource();
	//创建ThreadLocal
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//获取数据库连接池的连接对象
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	//获取数据源
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	//获得当前线程绑定的数据库连接对象
	public static Connection getCurrentConnection() throws SQLException {
		Connection conn=tl.get();
		if(conn==null) {
			conn=getConnection();
			tl.set(conn);
		}
		return conn;
	}
	//开启事务
	public static void startTransaction() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.setAutoCommit(false);//取消自动提交
	}
	//提交事务
	public static void commit() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.commit();
		tl.remove();//将该Connection对象从ThreadLocal中移除
		conn.close();
	}
	//回滚事务
	public static void rollback() throws SQLException {
		getCurrentConnection().rollback();
	}
	

}
