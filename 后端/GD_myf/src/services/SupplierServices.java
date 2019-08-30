package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.SupplierDao;
import dbUtils.MyDbUtils;
import domain.Supplier;

/**
 * ��Ӧ�̷���ҵ���
 * @author ë���
 * @caeateTime 2019��4��25������12:00:54
   @package_name services
	@file_name SupplierServices.java
 */

public class SupplierServices {
	//������Ӧ�����ݲ�������
	private static SupplierDao supDao =new SupplierDao();
	/**
	 * ��ӹ�Ӧ��
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
	//��ѯ���û������й�Ӧ��
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
	 * ɾ��ָ���û�id�͹�Ӧ��id�Ĺ�Ӧ����
	 * @param userId
	 * @param supId
	 * @return
	 */
	public boolean delete(String userId, String supId) {
		return supDao.delete(userId,supId);
	}
	/**
	 * ���ݹ�Ӧ��id���в�ѯ
	 * @param supId
	 * @return
	 */
	public Supplier queryById(String supId) {
		// TODO Auto-generated method stub
		return supDao.queryById(supId);
	}
	/**
	 * ��sup_user�����������
	 * @param userId
	 * @param supId
	 * @return
	 */
	public boolean addSup_user(String userId, String supId) {
		return supDao.addSup_user(userId, supId);
	}
	/**
	 * �޸Ĺ�Ӧ����Ϣ
	 * @param sup
	 * @return
	 */
	public boolean update(Supplier sup) {	
		return supDao.update(sup);
	}
	/**
	 * ���ݹ�Ӧ�����ƻ�id����ģ����ѯ
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
	 * �����û�id�͹�Ӧ��id�鿴sup_user�����Ƿ��������
	 * @param userId
	 * @param supId
	 * @return
	 */
	public boolean queryById_two(String userId, String supId) {
		return supDao.queryById_two(userId,supId);
	}
	

}
