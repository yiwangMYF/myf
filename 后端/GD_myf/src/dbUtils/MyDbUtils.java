package dbUtils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݿ⹤����
 * @author ë���
 * @caeateTime 2019��4��10������8:43:06
   @package_name dbUtils
	@file_name DbUtils.java
 */

public class MyDbUtils {
	//����c3p0���ݿ����ӳض���
	private static ComboPooledDataSource dataSource= new ComboPooledDataSource();
	//����ThreadLocal
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//��ȡ���ݿ����ӳص����Ӷ���
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	//��ȡ����Դ
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	//��õ�ǰ�̰߳󶨵����ݿ����Ӷ���
	public static Connection getCurrentConnection() throws SQLException {
		Connection conn=tl.get();
		if(conn==null) {
			conn=getConnection();
			tl.set(conn);
		}
		return conn;
	}
	//��������
	public static void startTransaction() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.setAutoCommit(false);//ȡ���Զ��ύ
	}
	//�ύ����
	public static void commit() throws SQLException {
		Connection conn = getCurrentConnection();
		conn.commit();
		tl.remove();//����Connection�����ThreadLocal���Ƴ�
		conn.close();
	}
	//�ع�����
	public static void rollback() throws SQLException {
		getCurrentConnection().rollback();
	}
	

}
