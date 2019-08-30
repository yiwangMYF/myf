package services;

import java.sql.Connection;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import dao.MedicineDao;
import dbUtils.MyDbUtils;
import domain.Frmloss_bill;
import domain.Medicine;
import domain.Medicine_Sale;
import domain.Medicine_base;
import domain.Medicine_stock;
import domain.Medicine_stock_date;
import domain.PageBean;
import domain.Purchase_bill;
import domain.Return_bill;
import domain.StockInfo;

/**
 * ҩƷ��ع���ҵ���
 * @author ë���
 * @caeateTime 2019��4��28������9:27:31
   @package_name services
	@file_name MedicineService.java
 */

public class MedicineService {
	private static MedicineDao medDao = new MedicineDao();
	
	/**
	 * ���ҩƷ�Ļ�����Ϣ������ҩƷ����׼�ĺš�ͨ��������Ӧ��id��������
	 * @param medi
	 * @return
	 */
	public void addBaseInfo(Medicine medi) {
		//�Ȳ�ѯ���ݿ����Ƿ��Ѿ����ڸ�ҩƷ�Ļ�����Ϣ
		Boolean isExist=checkBaseInfo(medi.getmId());
		if(isExist==false) {
			medDao.addBaseInfo(medi);
		}
	}
	/**
	 * ����ҩƷ����׼�ĺŲ�ѯ��ҩƷ�Ļ�����Ϣ�Ƿ��Ѿ������ݿ���
	 * @param getmId
	 * @return
	 */
	private Boolean checkBaseInfo(String mId) {
		return medDao.checkBaseInfo(mId);
	}
	/**
	 * ���ҩƷ��������Ϣ
	 * @param medi
	 */
	public void addLotInfo(Medicine medi) {
		//�Ȳ�ѯ������Ϣ�����Ƿ��Ѵ��ڸ�ҩƷ�ĸ�������Ϣ
		Boolean isExist =checkLotInfo(medi.getmId(),medi.getmLotNum());
		if(isExist==false) {
			medDao.addLotInfo(medi);
		}
		
	}
	/**
	 * ��ѯ������Ϣ�����Ƿ��Ѵ��ڸ�ҩƷ�ĸ�������Ϣ
	 * @param getmId
	 * @param getmLotNum
	 * @return
	 */
	private Boolean checkLotInfo(String mId, String mLotNum) {
		return medDao.checkLotInfo(mId,mLotNum);
	}
	/**
	 * ҩƷ��⣬����ҩƷ��Ϣ��⡢ҩƷ������Ϣ��⡢������Ϣ���Ϳ����Ϣ�޸�
	 * @param medi
	 * @param purBill
	 */
	public Boolean addMedicine(Medicine medi, Purchase_bill purBill,String userId) {
		Boolean isSuccess=null;
		//��������
		try {
			MyDbUtils.startTransaction();
			//���ҩƷ������Ϣ
			addBaseInfo(medi);
			//���ҩƷ������Ϣ
			addLotInfo(medi);
			//��Ӷ�����Ϣ
			addPurBill(userId,purBill);
			//�޸Ŀ����Ϣ
			System.out.println("----��ʼ�޸Ŀ����Ϣ-----");
			updateStock(userId,purBill);
			MyDbUtils.commit();
			isSuccess=true;
			
		} catch (SQLException e) {
			isSuccess=false;
			try {
				//ʧ������ع�
				MyDbUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isSuccess;
	
	}
	/**
	 * ���������Ķ�����Ϣ�޸Ŀ����Ϣ
	 * @param userId
	 * @param purBill
	 * @throws SQLException 
	 */
	public void updateStock(String userId, Purchase_bill purBill) throws SQLException {
		//��ѯ��ǰ�û��������Ƿ��и�����ҩƷ�ļ�¼
		Boolean isExist=checkStock(userId,purBill);
		if(isExist==true) {
			//�޸�ԭ��������		
			medDao.updateStock_add(userId,purBill);
		}else {
			//�ڿ���������һ����¼
			medDao.insertStock(userId,purBill);
		}
	}
	/**
	 * ��ѯ��ǰ�û��������Ƿ��и�����ҩƷ�ļ�¼
	 * @param userId
	 * @param purBill
	 * @return
	 * @throws SQLException 
	 */
	private Boolean checkStock(String userId, Purchase_bill purBill) throws SQLException {
		return medDao.checkStock(userId,purBill);
	}
	/**
	 * ��ӽ���������Ϣ
	 * @param userId
	 * @param purBill
	 * @throws SQLException 
	 */
	private void addPurBill(String userId, Purchase_bill purBill) throws SQLException {
		medDao.addPurBill(userId,purBill);	
	}
	/**
	 * ��ȡҩƷ���ķ�ҳ��Ϣ
	 * @param userId
	 * @param currentPage
	 * @return
	 */
	public PageBean<StockInfo> getPageBean_stock(String userId, int currentPage) {
		PageBean<StockInfo> pageBean=null;
		//��ȡҩƷ������������Ŀ
		int totalRecord =getTotalMedicine(userId);
		System.out.println("totalRecord:"+totalRecord);
		//���㵱ǰҳ�Ŀ�ʼ����
		int startIndex=(currentPage-1)*8;
		//��ȡ��ǰҳ������
		List<StockInfo> list =medDao.getPageData(userId,startIndex,8);
		pageBean=new PageBean<StockInfo>(currentPage, totalRecord, list);
		return pageBean;
	}
	//��ȡ�û�����ҩƷ������Ŀ
	private int getTotalMedicine(String userId){
		int count=0;
		try {
			count=medDao.getTotalMedicine(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * ��ѯҩƷ���ۼ�
	 * @param userId
	 * @param mId
	 * @return
	 */
	public Medicine_Sale queryOutPrice(String userId, String mId) {
		return medDao.queryOutPrice(userId,mId);
	
	}
	/**
	 * ����ҩƷ�۸�
	 * @param userId
	 * @param mId
	 * @param outPrice
	 */
	public void setPrice(String userId, String mId, String outPrice) {
		//��ѯҩƷ�Ƿ��������ۼ�
		Medicine_Sale medSale=queryOutPrice(userId, mId);
		if(medSale==null) {
			medDao.setPrice(userId,mId,outPrice);
		}else {
			medDao.updatePrice(userId,mId,outPrice);
		}
		
	}
	/**
	 * ����ҩƷ���ƽ���ģ����ѯ����ѯ�����Ϣ
	 * @param userId
	 * @param mName
	 * @return
	 */
	public List<StockInfo> getDataWithMId(String userId, String mName) {
		
		return medDao.getDataWithMId(userId,mName);
	}
	/**
	 * ��õ�ǰҩƷ���εĿ������
	 * @param userId
	 * @param mId
	 * @param mLotNum
	 * @return
	 */
	public int getStock(String userId, String mId, String mLotNum) {
		// TODO Auto-generated method stub
		return medDao.getStock(userId,mId,mLotNum);
	}
	/**
	 * ʵ��ҩƷ�˻�
	 * @param userId
	 * @param rBill
	 */
	public Boolean returnMedicine(String userId, Return_bill rBill) {
		Boolean isSuccess=null;
		//��������
		try {
			System.out.println("hhhhhhhhhhhhhh");
			MyDbUtils.startTransaction();
			//����˻���¼
			addReturnBill(userId,rBill);
			//�޸Ŀ������
			updateStock_reduce(userId,rBill.getmId(),rBill.getmLotNum(),rBill.getrNum());
			MyDbUtils.commit();
			isSuccess=true;
		} catch (SQLException e) {
			isSuccess=false;
			try {
				MyDbUtils.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
	/**
	 * ���ٿ����
	 * @param userId
	 * @param rBill
	 */
	public void updateStock_reduce(String userId,String mId,String mLotNum,int num) {
		try {
			medDao.updateStock_reduce(userId,mId,mLotNum,num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * ����˻���¼
	 * @param userId
	 * @param rBill
	 */
	private void addReturnBill(String userId, Return_bill rBill) {
		medDao.addReturnBill(userId,rBill);
		
	}
	/**
	 * ҩƷ����
	 * @param userId
	 * @param lossBill
	 * @return
	 */
	public Boolean frmLoss(String userId, Frmloss_bill lossBill) {
		Boolean isSuccess=null;
		try {
			//��������
			MyDbUtils.startTransaction();
			//��ӱ����¼
			addLossBill(userId,lossBill);
			//�޸Ŀ����Ϣ
			updateStock_reduce(userId, lossBill.getmId(),lossBill.getmLotNum(),lossBill.getLossNum());
			MyDbUtils.commit();
			isSuccess=true;
		} catch (SQLException e) {
			isSuccess=false;
			try {
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
	 * ��ӱ����¼
	 * @param userId
	 * @param lossBill
	 */
	private void addLossBill(String userId, Frmloss_bill lossBill) {
		medDao.addLossBill(userId,lossBill);
		
	}
	/**
	 * ����ҩƷ���ƽ���ģ����ѯҩƷ��Ϣ
	 * @param text
	 * @return
	 */
	public List<Medicine_base> fuzzyQueryByMedName(String text) {
		List<Medicine_base> list=null;
		try {
			list=medDao.fuzzyQueryByMedName(text);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ��ȡ���ں͹���ҩƷ���ݵķ�ҳ��Ϣ
	 * @param userId
	 * @param currentPage
	 * @return
	 */
	public PageBean<Medicine_stock_date> getPageBean_expList(String userId, int currentPage) {
		//��õ�ǰ����
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd");
		Date currentDate=null;
		try {
			 currentDate=sdf.parse(sdf.format(new Date()));
			 System.out.println("curDate:"+currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//��ý��ں͹���ҩƷ���ܼ�¼��
		int totalRecord =getNumOfOverDate(userId,currentDate);
		System.out.println(totalRecord+"aaa");
		if(totalRecord==0) {
			return null;
		}
		//���㵱ǰҳ�Ŀ�ʼ����
		int startIndex=(currentPage-1)*8;
		//��õ�ǰҳ���Ľ��ں͹���ҩƷ������
		List<Medicine_stock_date> list=getMedicineDataOver(userId,startIndex,8,currentDate);
		PageBean<Medicine_stock_date> pageBean =new PageBean<Medicine_stock_date>(currentPage, totalRecord, list);
		return pageBean;
	}
	/**
	 * ��ý��ں͹���ҩƷ�ķ�ҳ����
	 * @param userId
	 * @param startIndex
	 * @param i
	 * @return
	 */
	private List<Medicine_stock_date> getMedicineDataOver(String userId, int startIndex, int i,Date currentDate) {
		List<Medicine_stock_date> list=null;
		try {
			list= medDao.getMedicineDataOver(userId,startIndex,i,currentDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ��õ�ǰ������ָ���û��Ľ��ں͹���ҩƷ����
	 * @param userId
	 * @param currentDate
	 * @return
	 */
	private int getNumOfOverDate(String userId, Date currentDate) {
		return medDao.getNumOfOverDate(userId,currentDate);
	}
	//����ҩƷ���ƻ�õ�ǰ�û�ҩƷ�ĵ�ǰ�ۼ�
	public double getOutPriceByName(String userId, String mName) {
		//��ȡҩƷ����׼�ĺ�
		String mId =getmIdByName(mName);
		//��ȡ��ǰҩƷ���ۼۼ�¼
		Medicine_Sale ms =medDao.queryOutPrice(userId, mId);
		System.out.println(ms.toString());
		return ms.getOutPrice();	
	}
	//����ҩƷ���ƻ��ҩƷ����׼�ĺ�
	public String getmIdByName(String mName) {
		// TODO Auto-generated method stub
		return medDao.getmIdByName(mName);
	}
	/**
	 * �����û�id��ҩƷId���ָ��ҩƷ���Ƶ����п����Ϣ
	 * @param userId
	 * @param mName
	 * @return
	 * @throws SQLException 
	 */
	public List<Medicine_stock> getMedicineStock(String userId, String mId) {
		
		List<Medicine_stock> list=null;
		try {
			list=medDao.getMedicineStock(userId,mId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	/**
	 * ������ʼ���ڲ�ѯ���û���ҩƷ�������
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Map<String, Object>> getMedicineSales(String userId, Date startDate, Date endDate) {
		List<Map<String, Object>> list=null;
		try {
			list=medDao.getMeddicineSales(userId,startDate,endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ���ָ�����ڷ�Χ�ڵĽ�����֧��
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public double getPurTotalExpenses(String userId, Date startDate, Date endDate) {
		double pur_totalExpenses=0;
		try {
			pur_totalExpenses=medDao.getPurTotalExpenses(userId,startDate,endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pur_totalExpenses;
	}
	/**
	 * ���ָ�����ڷ�Χ�ڸ��û�ҩƷ������ܷ���
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public double getFrmlossTotalExpenses(String userId, Date startDate, Date endDate) {
		double frmloss_totalExpenses =0;
		try {
			frmloss_totalExpenses=medDao.getFrmlossTotalExpenses(userId,startDate,endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frmloss_totalExpenses;
	}
	/**
	 * ���ָ�����ڷ�Χ��ҩƷ�˻����ܷ���
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public double getReturnTotalExpenses(String userId, Date startDate, Date endDate) {
		double return_totalExpenses =0;
		try {
			return_totalExpenses = medDao.getReturnTotalExpenses(userId,startDate,endDate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return return_totalExpenses;
	}
	

}
