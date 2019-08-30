package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dbUtils.MyDbUtils;
import domain.Supplier;

/**
 * ��Ӧ�����ݲ�����
 * 
 * @author ë���
 * @caeateTime 2019��4��25������12:02:01
 * @package_name dao
 * @file_name SupplierDao.java
 */

public class SupplierDao {
	private static QueryRunner qr = new QueryRunner();

	/**
	 * ��Ӧ�̱����������
	 * 
	 * @param supId
	 * @param supName
	 * @param supTel
	 * @param supAddress
	 * @return
	 * @throws SQLException
	 */
	public boolean addSup(String supId, String supName, String supTel, String supAddress) throws SQLException {
		Connection con = MyDbUtils.getConnection();
		String sql = "insert into supplier values(?,?,?,?)";
		int row = qr.update(con, sql, supId, supName, supTel, supAddress);
		if (row == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ӹ�Ӧ���û���ϵ
	 * 
	 * @param userId
	 * @param supId
	 * @return
	 * @throws SQLException
	 */
	public Boolean addSup_user(String userId, String supId) {
		Connection con = null;
		String sql = "insert into sup_user(userId,supId) values(?,?)";
		int row = 0;
		try {
			con = MyDbUtils.getCurrentConnection();
			row = qr.update(con, sql, userId, supId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ��ѯ���û������й�Ӧ��
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<Supplier> query(String userId) throws SQLException {
		List<Supplier> supList = null;
		Connection con = MyDbUtils.getConnection();
		String sql = "Select supplier.supId,supplier.supName,supplier.supTel,supplier.supAddress from supplier,sup_user where sup_user.supId=supplier.supId and sup_user.userId=?";
		supList = qr.query(con, sql, new BeanListHandler<Supplier>(Supplier.class), userId);
		return supList;
	}

	public Boolean delete(String userId, String supId) {
		Connection con = null;
		String sql = "delete from sup_user where userId=? and supId=?";
		int row = 0;
		try {
			con = MyDbUtils.getConnection();
			row = qr.update(con, sql, userId, supId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (row != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ���ݹ�Ӧ��id���в�ѯ
	 * 
	 * @param supId
	 * @return
	 */
	public Supplier queryById(String supId) {
		Connection con = null;
		Supplier sup = null;
		String sql = "select * from supplier where supId=?";
		try {
			con = MyDbUtils.getConnection();
			sup = (Supplier) qr.query(con, sql, new BeanHandler(Supplier.class), supId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sup;
	}

	/**
	 * �޸Ĺ�Ӧ����Ϣ
	 * 
	 * @param sup
	 * @return
	 */
	public Boolean update(Supplier sup) {
		Boolean isSuccess = null;
		String sql = "update supplier set supName=?,supTel=?,supAddress=? where supId=?";
		try {
			// ��������
			MyDbUtils.startTransaction();
			int row = qr.update(MyDbUtils.getCurrentConnection(), sql, sup.getSupName(), sup.getSupTel(),
					sup.getSupAddress(), sup.getSupId());
			if (row == 1) {
				isSuccess = true;
			} else {
				isSuccess = false;
			}
			MyDbUtils.commit();
		} catch (SQLException e) {
			isSuccess = false;
			try {
				// �޸�ʧ�ܣ��ع�
				MyDbUtils.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * ���ݹ�Ӧ�����ƽ���ģ����ѯ
	 * 
	 * @param userId
	 * @param text
	 * @return
	 * @throws SQLException
	 */
	public List<Supplier> fuzzyQuery(String userId, String text) throws SQLException {
		Connection con = null;
		List<Supplier> supList = null;
		String sql = "Select supplier.supId,supplier.supName,supplier.supTel,supplier.supAddress from supplier,sup_user where sup_user.supId=supplier.supId and supplier.supName like ? and sup_user.userId=?";
		con = MyDbUtils.getConnection();
		String s = "%" + text + "%";
		supList = qr.query(con, sql, new BeanListHandler<Supplier>(Supplier.class), s, userId);
		return supList;
	}

	/**
	 * ��ѯ��ǰ�û��Ƿ��иù�Ӧ��
	 * 
	 * @param userId
	 * @param supId
	 * @return
	 */
	public Boolean queryById_two(String userId, String supId) {
		Connection con = null;
		int count = 0;
		String sql = "select count(*) from sup_user where userId=? and supId=?";
		try {
			con = MyDbUtils.getConnection();
			count = ((Long) qr.query(con, sql, new ScalarHandler<>(), userId, supId)).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count:" + count);
		if (count != 0) {
			return true;
		} else {
			return false;
		}

	}

}
