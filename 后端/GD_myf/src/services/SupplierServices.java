package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.SupplierDao;
import dbUtils.MyDbUtils;
import domain.Supplier;

/**
 * 供应商服务业务层
 * @author 毛燕丰
 * @caeateTime 2019年4月25日下午12:00:54
   @package_name services
	@file_name SupplierServices.java
 */

public class SupplierServices {
	//创建供应商数据操作对象
	private static SupplierDao supDao =new SupplierDao();
	/**
	 * 添加供应商
	 * @param userId
	 * @param supId
	 * @param supName
	 * @param supTel
	 * @param supAddress
	 */
	public boolean addSupInTwo(String userId, String supId, String supName, String supTel, String supAddress) {
		Boolean isSuccess=false;
		try {
			
			Boolean ok1=supDao.addSup(supId,supName,supTel,supAddress);
			Boolean ok2=supDao.addSup_user(userId,supId);
			if(ok1==true&&ok2==true) {
				isSuccess=true;
			}
		} catch (SQLException e) {
			isSuccess=false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
	//查询该用户的所有供应商
	public List<Supplier> query(String userId)  {
		List<Supplier> list=null;
		try {
			list= supDao.query(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	/**
	 * 删除指定用户id和供应商id的供应数据
	 * @param userId
	 * @param supId
	 * @return
	 */
	public boolean delete(String userId, String supId) {
		return supDao.delete(userId,supId);
	}
	/**
	 * 根据供应商id进行查询
	 * @param supId
	 * @return
	 */
	public Supplier queryById(String supId) {
		// TODO Auto-generated method stub
		return supDao.queryById(supId);
	}
	/**
	 * 向sup_user表中添加数据
	 * @param userId
	 * @param supId
	 * @return
	 */
	public boolean addSup_user(String userId, String supId) {
		return supDao.addSup_user(userId, supId);
	}
	/**
	 * 修改供应商信息
	 * @param sup
	 * @return
	 */
	public boolean update(Supplier sup) {	
		return supDao.update(sup);
	}
	/**
	 * 根据供应商名称或id进行模糊查询
	 * @param userId
	 * @param text
	 * @return
	 */
	public List<Supplier> fuzzyQuery(String userId, String text) {
		List<Supplier> list=null;
		try {
			list= supDao.fuzzyQuery(userId,text);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据用户id和供应商id查看sup_user表中是否存在数据
	 * @param userId
	 * @param supId
	 * @return
	 */
	public boolean queryById_two(String userId, String supId) {
		return supDao.queryById_two(userId,supId);
	}
	

}
