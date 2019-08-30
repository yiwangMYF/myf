package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dbUtils.MyDbUtils;
import domain.Patient;
import domain.Prescription;

/**
 * �������ݲ�����
 * @author ë���
 * @caeateTime 2019��5��15������7:39:54
   @package_name dao
	@file_name PrescriptionDao.java
 */

public class PrescriptionDao {
	private static QueryRunner qr = new QueryRunner(MyDbUtils.getDataSource());
	/**
	 * ���ݻ����籣�Ų�ѯ������Ϣ
	 * @param pId
	 * @return
	 */
	public Patient getPatientById(String pId) {
		Connection con =null;
		Patient p=null;
		String sql="select * from patient where pId=?";
		try {
			con=MyDbUtils.getCurrentConnection();
			p=qr.query(con, sql, new BeanHandler<Patient>(Patient.class), pId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	/**
	 * ��ӻ�����Ϣ��¼
	 * @param pId
	 * @param pName
	 * @param pSex
	 * @param pBirth
	 * @throws SQLException 
	 */
	public void addPatient(String pId, String pName, String pSex, Date pBirth) throws SQLException {
		Connection con =null;
		String sql = "insert into patient values(?,?,?,?)";
		
		con=MyDbUtils.getCurrentConnection();
		qr.update(con, sql, pId,pName,pSex,pBirth);
	
		
	}
	/**
	 * ��Ӵ�����Ϣ��¼
	 * @param pre
	 * @throws SQLException 
	 */
	public void addPrescription(Prescription pre) throws SQLException {
		Connection con =null;
		String sql="insert into prescription values(?,?,?,?,?,?)";
		
		con=MyDbUtils.getCurrentConnection();
		qr.update(con, sql, pre.getpId(),pre.getClinical_diagnosis(),pre.getMed_details(),pre.getUserId(),pre.getCost(),pre.getIssue_date());
		
		
	}
	/**
	 * ���ݻ����籣���Ų�ѯ���ߵ����в�����Ϣ
	 * @param pId
	 * @return
	 * @throws SQLException 
	 */
	public List<Prescription> getPreList(String pId) throws SQLException {
		List<Prescription> list=null;
		Connection con = null;
		String sql="select patient.pId,patient.pName,patient.pSex,patient.pBirth,prescription.clinical_diagnosis,prescription.med_details,prescription.userId,prescription.issue_date,prescription.cost from prescription,patient where patient.pId=prescription.pId and patient.pId=?";
		con=MyDbUtils.getCurrentConnection();
		list=qr.query(con, sql, new BeanListHandler<Prescription>(Prescription.class), pId);
		return list;
	}
	/**
	 * ���ҩƷ������������¼
	 * @param mName
	 * @param num
	 * @param pId
	 * @throws SQLException 
	 */
	public void addMedSaleNum(String mName, int num, String pId,String userId,Date issue_date) throws SQLException {
		Connection con=null;
		String sql="insert into medicinal_sale_num values(?,?,?,?,?)";
		con=MyDbUtils.getCurrentConnection();
		qr.update(con, sql,userId,pId,mName,num,issue_date);
	}
	/**
	 * ���ָ�����ڷ�Χ���û���ҩƷ�����۶�
	 * @param userId
	 * @param startdate
	 * @param enddate
	 * @return
	 * @throws SQLException 
	 */
	public double getSales(String userId, Date startdate, Date enddate) throws SQLException {
		Connection con=null;
		double sales=0;
		String sql="select coalesce(sum(cost),0) as sales from prescription where userId=? and issue_date between ? and ?";
		con=MyDbUtils.getCurrentConnection();
		sales=(double) qr.query(con, sql, new ScalarHandler(),userId,startdate,enddate);
		return sales;
	}

}
