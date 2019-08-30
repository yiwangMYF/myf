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
 * 处方管理业务层
 * 
 * @author 毛燕丰
 * @caeateTime 2019年5月15日上午7:38:51
 * @package_name services
 * @file_name PrescriptionServices.java
 */

public class PrescriptionServices {
	private static PrescriptionDao preDao = new PrescriptionDao();

	/**
	 * 添加处方信息
	 * 
	 * @param pre
	 * @param med
	 * @return
	 */
	public Boolean addPreInfo(Prescription pre, String med) {
		Boolean isSuccess = null;
		// 开启事务
		try {
			MyDbUtils.startTransaction();
			// 判断患者信息是否已经存在
			Boolean isExist = checkPatient(pre.getpId());
			System.out.println("2");
			if (isExist == false) {
				// 添加患者信息记录
				addPatient(pre.getpId(), pre.getpName(), pre.getpSex(), pre.getpBirth());
			}
			// 添加处方记录
			preDao.addPrescription(pre);
			// 添加药品使用记录和修改库存
			JSONArray jsonArr = new JSONArray(med);
			for (int i = 0; i < jsonArr.length(); i++) {
				JSONObject json = jsonArr.getJSONObject(i);
				String mName = json.getString("name");
				String num_str = json.getString("num");
				int num = Integer.parseInt(num_str);
				// 添加销售记录
				addMedSaleNum(mName, num, pre.getpId(), pre.getUserId(), pre.getIssue_date());
				MedicineService medService = new MedicineService();
				// 根据药品名称查询其批准文号
				String mId = medService.getmIdByName(mName);
				List<Medicine_stock> stockList = medService.getMedicineStock(pre.getUserId(), mId);
				// 修改库存
				for (Medicine_stock medStock : stockList) {
					if (medStock.getsNum() >= num) {
						// 减少库存
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
	 * 添加处方中个药品的销量记录
	 * 
	 * @param mName
	 * @param num
	 * @param getpId
	 * @throws SQLException
	 */
	private void addMedSaleNum(String mName, int num, String pId, String userId, Date issue_date) throws SQLException {
		preDao.addMedSaleNum(mName, num, pId, userId, issue_date);

	}

	// 添加患者信息记录
	private void addPatient(String pId, String pName, String pSex, Date pBirth) throws SQLException {
		preDao.addPatient(pId, pName, pSex, pBirth);

	}

	// 根据患者社保号查询该患者信息是否已在数据库
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

	// 根据患者社保卡号查询其所有的病历信息
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
	 * 获得指定日期范围内用户的销售总额
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
