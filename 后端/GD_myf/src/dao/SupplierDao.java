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
 * 供应商数据操作层
 * 
 * @author 毛燕丰
 * @caeateTime 2019年4月25日下午12:02:01
 * @package_name dao
 * @file_name SupplierDao.java
 */

public class SupplierDao {
	private static QueryRunner qr = new QueryRunner();

	/**
	 * 向供应商表中添加数据
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
	 * 添加供应—用户联系
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
	 * 查询该用户的所有供应商
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
	 * 根据供应商id进行查询
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
	 * 修改供应商信息
	 * 
	 * @param sup
	 * @return
	 */
	public Boolean update(Supplier sup) {
		Boolean isSuccess = null;
		String sql = "update supplier set supName=?,supTel=?,supAddress=? where supId=?";
		try {
			// 开启事务
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
				// 修改失败，回滚
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
	 * 根据供应商名称进行模糊查询
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
	 * 查询当前用户是否有该供应商
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
