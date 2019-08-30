package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import dbUtils.MyDbUtils;
import domain.User;

/**
 * �û����ݲ�����
 * 
 * @author ë���
 * @caeateTime 2019��4��10������10:25:03
 * @package_name dao
 * @file_name UserDao.java
 */

public class UserDao {

	private static QueryRunner qr = new QueryRunner();

	// �����û����֤�ź������ѯ�û�
	public User queryByid_ps(String userId, String userPassword) {
		String sql = "select * from user where userId=? and userPassword=?";
		Connection conn = null;
		User user = null;
		try {
			// ��ȡ���ݿ�����
			conn = MyDbUtils.getConnection();
			user = qr.query(conn, sql, new BeanHandler<>(User.class), userId, userPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	// ͨ���û�Id��ѯ�û�
	public User queryById(String userId) {
		String sql = "select * from user where userId=?";
		Connection conn = null;
		User user = null;
		try {
			conn = MyDbUtils.getConnection();
			user = qr.query(conn, sql, new BeanHandler<>(User.class), userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	// �����ݿ����һ���û�
	public boolean insertUser(String userId, String userName, String userPassword, String userGender) {
		String sql = "insert into user values(?,?,?,?)";
		Connection conn = null;
		int row = 0;
		try {
			conn = MyDbUtils.getConnection();
			row = qr.update(conn, sql, userId, userName, userPassword, userGender);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (row == 1) {
			return true;
		} else {
			return false;
		}

	}

	// �޸��û�����
	public boolean setNewPassword(String userId, String newPassword) throws SQLException {
		String sql = "update user set userPassword=? where userId=?";
		Connection conn = null;
		int row = 0;
		conn = MyDbUtils.getConnection();
		row = qr.update(conn, sql,newPassword,userId);
		if(row==1) {
			return true;
		}else {
			return false;
		}
		
	}

}
