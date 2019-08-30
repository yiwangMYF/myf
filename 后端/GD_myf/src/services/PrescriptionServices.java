package services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.PrescriptionDao;
import dbUtils.MyDbUtils;
import domain.Medicine_stock;
import domain.Patient;
import domain.Prescription;

/**
 * ��������ҵ���
 * 
 * @author ë���
 * @caeateTime 2019��5��15������7:38:51
 * @package_name services
 * @file_name PrescriptionServices.java
 */

public class PrescriptionServices {
	private static PrescriptionDao preDao = new PrescriptionDao();

	/**
	 * ��Ӵ�����Ϣ
	 * 
	 * @param pre
	 * @param med
	 * @return
	 */
	public Boolean addPreInfo(Prescription pre, String med) {
		Boolean isSuccess = null;
		// ��������
		try {
			MyDbUtils.startTransaction();
			// �жϻ�����Ϣ�Ƿ��Ѿ�����
			Boolean isExist = checkPatient(pre.getpId());
			System.out.println("2");
			if (isExist == false) {
				// ��ӻ�����Ϣ��¼
				addPatient(pre.getpId(), pre.getpName(), pre.getpSex(), pre.getpBirth());
			}
			// ��Ӵ�����¼
			preDao.addPrescription(pre);
			// ���ҩƷʹ�ü�¼���޸Ŀ��
			JSONArray jsonArr = new JSONArray(med);
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject json = jsonArr.getJSONObject(i);
				String mName = json.getString("name");
				String num_str = json.getString("num");
				int num = Integer.parseInt(num_str);
				// ������ۼ�¼
				addMedSaleNum(mName, num, pre.getpId(), pre.getUserId(), pre.getIssue_date());
				MedicineService medService = new MedicineService();
				// ����ҩƷ���Ʋ�ѯ����׼�ĺ�
				String mId = medService.getmIdByName(mName);
				List<Medicine_stock> stockList = medService.getMedicineStock(pre.getUserId(), mId);
				// �޸Ŀ��
				for (Medicine_stock medStock : stockList) {
					if (medStock.getsNum() >= num) {
						// ���ٿ��
						medService.updateStock_reduce(medStock.getUserId(), medStock.getmId(), medStock.getmLotNum(),
								num);
						break;
					} else {
						medService.updateStock_reduce(medStock.getUserId(), medStock.getmId(), medStock.getmLotNum(),
								num);
						num = num - medStock.getsNum();
					}
				}
			}
			MyDbUtils.commit();
			isSuccess = true;
		} catch (SQLException e) {
			isSuccess = false;
			try {
				MyDbUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * ��Ӵ����и�ҩƷ��������¼
	 * 
	 * @param mName
	 * @param num
	 * @param getpId
	 * @throws SQLException
	 */
	private void addMedSaleNum(String mName, int num, String pId, String userId, Date issue_date) throws SQLException {
		preDao.addMedSaleNum(mName, num, pId, userId, issue_date);

	}

	// ��ӻ�����Ϣ��¼
	private void addPatient(String pId, String pName, String pSex, Date pBirth) throws SQLException {
		preDao.addPatient(pId, pName, pSex, pBirth);

	}

	// ���ݻ����籣�Ų�ѯ�û�����Ϣ�Ƿ��������ݿ�
	private Boolean checkPatient(String pId) {
		Boolean isExist = null;
		Patient p = preDao.getPatientById(pId);
		if (p == null) {
			isExist = false;
		} else {
			isExist = true;
		}
		return isExist;
	}

	// ���ݻ����籣���Ų�ѯ�����еĲ�����Ϣ
	public List<Prescription> getPreList(String pId) {
		List<Prescription> list = null;
		try {
			list = preDao.getPreList(pId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * ���ָ�����ڷ�Χ���û��������ܶ�
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException 
	 */
	public double getSales(String userId, java.util.Date startDate, java.util.Date endDate) throws SQLException {
		double sales = 0;
		java.sql.Date startdate = new java.sql.Date(startDate.getTime());
		java.sql.Date enddate = new java.sql.Date(endDate.getTime());
		sales = preDao.getSales(userId, startdate, enddate);
		return sales;
	}

}
