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
 * 药品相关功能业务层
 * @author 毛燕丰
 * @caeateTime 2019年4月28日下午9:27:31
   @package_name services
	@file_name MedicineService.java
 */

public class MedicineService {
	private static MedicineDao medDao = new MedicineDao();
	
	/**
	 * 添加药品的基本信息（包括药品的批准文号、通用名、供应商id、规格、类别）
	 * @param medi
	 * @return
	 */
	public void addBaseInfo(Medicine medi) {
		//先查询数据库中是否已经存在该药品的基本信息
		Boolean isExist=checkBaseInfo(medi.getmId());
		if(isExist==false) {
			medDao.addBaseInfo(medi);
		}
	}
	/**
	 * 根据药品的批准文号查询该药品的基本信息是否已经在数据库中
	 * @param getmId
	 * @return
	 */
	private Boolean checkBaseInfo(String mId) {
		return medDao.checkBaseInfo(mId);
	}
	/**
	 * 添加药品的批次信息
	 * @param medi
	 */
	public void addLotInfo(Medicine medi) {
		//先查询批次信息表中是否已存在该药品的该批次信息
		Boolean isExist =checkLotInfo(medi.getmId(),medi.getmLotNum());
		if(isExist==false) {
			medDao.addLotInfo(medi);
		}
		
	}
	/**
	 * 查询批次信息表中是否已存在该药品的该批次信息
	 * @param getmId
	 * @param getmLotNum
	 * @return
	 */
	private Boolean checkLotInfo(String mId, String mLotNum) {
		return medDao.checkLotInfo(mId,mLotNum);
	}
	/**
	 * 药品入库，包括药品信息入库、药品批次信息入库、订单信息入库和库存信息修改
	 * @param medi
	 * @param purBill
	 */
	public Boolean addMedicine(Medicine medi, Purchase_bill purBill,String userId) {
		Boolean isSuccess=null;
		//开启事务
		try {
			MyDbUtils.startTransaction();
			//添加药品基本信息
			addBaseInfo(medi);
			//添加药品批次信息
			addLotInfo(medi);
			//添加订单信息
			addPurBill(userId,purBill);
			//修改库存信息
			System.out.println("----开始修改库存信息-----");
			updateStock(userId,purBill);
			MyDbUtils.commit();
			isSuccess=true;
			
		} catch (SQLException e) {
			isSuccess=false;
			try {
				//失败事务回滚
				MyDbUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isSuccess;
	
	}
	/**
	 * 根据新增的订单信息修改库存信息
	 * @param userId
	 * @param purBill
	 * @throws SQLException 
	 */
	public void updateStock(String userId, Purchase_bill purBill) throws SQLException {
		//查询当前用户库存表中是否有该批次药品的记录
		Boolean isExist=checkStock(userId,purBill);
		if(isExist==true) {
			//修改原来的数据		
			medDao.updateStock_add(userId,purBill);
		}else {
			//在库存表中新增一条记录
			medDao.insertStock(userId,purBill);
		}
	}
	/**
	 * 查询当前用户库存表中是否有该批次药品的记录
	 * @param userId
	 * @param purBill
	 * @return
	 * @throws SQLException 
	 */
	private Boolean checkStock(String userId, Purchase_bill purBill) throws SQLException {
		return medDao.checkStock(userId,purBill);
	}
	/**
	 * 添加进货订单信息
	 * @param userId
	 * @param purBill
	 * @throws SQLException 
	 */
	private void addPurBill(String userId, Purchase_bill purBill) throws SQLException {
		medDao.addPurBill(userId,purBill);	
	}
	/**
	 * 获取药品库存的分页信息
	 * @param userId
	 * @param currentPage
	 * @return
	 */
	public PageBean<StockInfo> getPageBean_stock(String userId, int currentPage) {
		PageBean<StockInfo> pageBean=null;
		//获取药品库存种类的总数目
		int totalRecord =getTotalMedicine(userId);
		System.out.println("totalRecord:"+totalRecord);
		//计算当前页的开始索引
		int startIndex=(currentPage-1)*8;
		//获取当前页的数据
		List<StockInfo> list =medDao.getPageData(userId,startIndex,8);
		pageBean=new PageBean<StockInfo>(currentPage, totalRecord, list);
		return pageBean;
	}
	//获取用户库存的药品种类数目
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
	 * 查询药品的售价
	 * @param userId
	 * @param mId
	 * @return
	 */
	public Medicine_Sale queryOutPrice(String userId, String mId) {
		return medDao.queryOutPrice(userId,mId);
	
	}
	/**
	 * 设置药品价格
	 * @param userId
	 * @param mId
	 * @param outPrice
	 */
	public void setPrice(String userId, String mId, String outPrice) {
		//查询药品是否有设置售价
		Medicine_Sale medSale=queryOutPrice(userId, mId);
		if(medSale==null) {
			medDao.setPrice(userId,mId,outPrice);
		}else {
			medDao.updatePrice(userId,mId,outPrice);
		}
		
	}
	/**
	 * 根据药品名称进行模糊查询，查询库存信息
	 * @param userId
	 * @param mName
	 * @return
	 */
	public List<StockInfo> getDataWithMId(String userId, String mName) {
		
		return medDao.getDataWithMId(userId,mName);
	}
	/**
	 * 获得当前药品批次的库存数量
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
	 * 实现药品退货
	 * @param userId
	 * @param rBill
	 */
	public Boolean returnMedicine(String userId, Return_bill rBill) {
		Boolean isSuccess=null;
		//开启事务
		try {
			System.out.println("hhhhhhhhhhhhhh");
			MyDbUtils.startTransaction();
			//添加退货记录
			addReturnBill(userId,rBill);
			//修改库存数量
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
	 * 减少库存量
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
	 * 添加退货记录
	 * @param userId
	 * @param rBill
	 */
	private void addReturnBill(String userId, Return_bill rBill) {
		medDao.addReturnBill(userId,rBill);
		
	}
	/**
	 * 药品报损
	 * @param userId
	 * @param lossBill
	 * @return
	 */
	public Boolean frmLoss(String userId, Frmloss_bill lossBill) {
		Boolean isSuccess=null;
		try {
			//开启事务
			MyDbUtils.startTransaction();
			//添加报损记录
			addLossBill(userId,lossBill);
			//修改库存信息
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
	 * 添加报损记录
	 * @param userId
	 * @param lossBill
	 */
	private void addLossBill(String userId, Frmloss_bill lossBill) {
		medDao.addLossBill(userId,lossBill);
		
	}
	/**
	 * 根据药品名称进行模糊查询药品信息
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
	 * 获取近期和过期药品数据的分页信息
	 * @param userId
	 * @param currentPage
	 * @return
	 */
	public PageBean<Medicine_stock_date> getPageBean_expList(String userId, int currentPage) {
		//获得当前日期
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd");
		Date currentDate=null;
		try {
			 currentDate=sdf.parse(sdf.format(new Date()));
			 System.out.println("curDate:"+currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//获得近期和过期药品的总记录数
		int totalRecord =getNumOfOverDate(userId,currentDate);
		System.out.println(totalRecord+"aaa");
		if(totalRecord==0) {
			return null;
		}
		//计算当前页的开始索引
		int startIndex=(currentPage-1)*8;
		//获得当前页数的近期和过期药品的数据
		List<Medicine_stock_date> list=getMedicineDataOver(userId,startIndex,8,currentDate);
		PageBean<Medicine_stock_date> pageBean =new PageBean<Medicine_stock_date>(currentPage, totalRecord, list);
		return pageBean;
	}
	/**
	 * 获得近期和过期药品的分页数据
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
	 * 获得当前日期下指定用户的近期和过期药品总数
	 * @param userId
	 * @param currentDate
	 * @return
	 */
	private int getNumOfOverDate(String userId, Date currentDate) {
		return medDao.getNumOfOverDate(userId,currentDate);
	}
	//根据药品名称获得当前用户药品的当前售价
	public double getOutPriceByName(String userId, String mName) {
		//获取药品的批准文号
		String mId =getmIdByName(mName);
		//获取当前药品的售价记录
		Medicine_Sale ms =medDao.queryOutPrice(userId, mId);
		System.out.println(ms.toString());
		return ms.getOutPrice();	
	}
	//根据药品名称获得药品的批准文号
	public String getmIdByName(String mName) {
		// TODO Auto-generated method stub
		return medDao.getmIdByName(mName);
	}
	/**
	 * 根据用户id和药品Id获得指定药品名称的所有库存信息
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
	 * 根据起始日期查询该用户的药品销售情况
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
	 * 获得指定日期范围内的进货总支出
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
	 * 获得指定日期范围内该用户药品耗损的总费用
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
	 * 获得指定日期范围内药品退货的总费用
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
