package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dbUtils.MyDbUtils;
import domain.Frmloss_bill;
import domain.Medicine;
import domain.Medicine_Sale;
import domain.Medicine_base;
import domain.Medicine_stock;
import domain.Medicine_stock_date;
import domain.Purchase_bill;
import domain.Return_bill;
import domain.StockInfo;

/**
 * 药品业务数据操作层
 * 
 * @author 毛燕丰
 * @caeateTime 2019年4月28日下午9:29:11
 * @package_name dao
 * @file_name MedicineDao.java
 */

public class MedicineDao {
	private static QueryRunner qr = new QueryRunner();

	/**
	 * 根据药品的批准文号查询数据库中是否存储该药品的基本信息
	 * 
	 * @param mId
	 * @return
	 */
	public Boolean checkBaseInfo(String mId) {
		Connection con = null;
		int count = 0;
		String sql = "select count(*) from medicinal_base where mId=?";
		try {
			con = MyDbUtils.getCurrentConnection();
			count = ((Long) qr.query(con, sql, new ScalarHandler<>(), mId)).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count != 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 添加药品的基本信息
	 * 
	 * @param medi
	 */
	public void addBaseInfo(Medicine medi) {
		Connection con = null;
		String sql = "insert into medicinal_base values(?,?,?,?,?)";
		try {
			con = MyDbUtils.getCurrentConnection();
			qr.update(con, sql, medi.getmId(), medi.getmName(), medi.getSupId(), medi.getmSpec(), medi.getmCateGory());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 查询批次信息表中是否已存在该药品的该批次信息
	 * 
	 * @param mId
	 * @param mLotNum
	 * @return
	 */
	public Boolean checkLotInfo(String mId, String mLotNum) {
		Connection con = null;
		int count = 0;
		String sql = "select count(*) from medicinal_lotnum where mId=? and mLotNum=?";
		try {
			con = MyDbUtils.getCurrentConnection();
			count = ((Long) qr.query(con, sql, new ScalarHandler<>(), mId, mLotNum)).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 添加药品的批次信息
	 * 
	 * @param medi
	 */
	public void addLotInfo(Medicine medi) {
		Connection con = null;
		String sql = "insert into medicinal_lotnum(mId,mLotNum,mPd,mExp) values(?,?,?,?)";
		try {
			con = MyDbUtils.getCurrentConnection();
			qr.update(con, sql, medi.getmId(), medi.getmLotNum(), medi.getmPd(), medi.getmExp());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 添加进货订单信息
	 * 
	 * @param userId
	 * @param purBill
	 * @throws SQLException
	 */
	public void addPurBill(String userId, Purchase_bill purBill) throws SQLException {
		Connection con = null;
		String sql = "insert into purchase_bill values(?,?,?,?,?,?,?,?)";
		con = MyDbUtils.getCurrentConnection();
		qr.update(con, sql, userId, purBill.getpurId(), purBill.getmId(), purBill.getmLotNum(), purBill.getPurNum(),
				purBill.getpurPrice(), purBill.getTotal(), purBill.getEnterDate());
	}

	/**
	 * 查询当前用户库存表中是否有该批次药品的记录
	 * 
	 * @param userId
	 * @param purBill
	 * @return
	 * @throws SQLException
	 */
	public Boolean checkStock(String userId, Purchase_bill purBill) throws SQLException {
		Connection con = null;
		int count = 0;
		String sql = "select count(*) from medicinal_stock where userId=? and mId=? and mLotNum=?";
		con = MyDbUtils.getCurrentConnection();
		count = ((Long) qr.query(con, sql, new ScalarHandler<>(), userId, purBill.getmId(), purBill.getmLotNum()))
				.intValue();
		if (count != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 修改库存表中的信息
	 * 
	 * @param userId
	 * @param purBill
	 * @throws SQLException
	 */
	public void updateStock_add(String userId, Purchase_bill purBill) throws SQLException {
		Connection con = null;
		String sql = "update medicinal_stock set sNum=sNum+? where userId=? and mId=? and mLotNum=?";
		con = MyDbUtils.getCurrentConnection();
		qr.update(con, sql, purBill.getPurNum(), userId, purBill.getmId(), purBill.getmLotNum());

	}

	/**
	 * 在库存表中新增数据
	 * 
	 * @param userId
	 * @param purBill
	 * @throws SQLException
	 */
	public void insertStock(String userId, Purchase_bill purBill) throws SQLException {
		Connection con = null;
		String sql = "insert into medicinal_stock values(?,?,?,?)";
		con = MyDbUtils.getCurrentConnection();
		qr.update(con, sql, userId, purBill.getmLotNum(), purBill.getmId(), purBill.getPurNum());

	}

	/**
	 * 获取用户库存药品种类数目
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int getTotalMedicine(String userId) throws SQLException {
		int count = 0;
		Connection con = null;
		String sql = "select count(*) from (select * from medicinal_stock where userId=? group by mId) as a";
		con = MyDbUtils.getConnection();
		count = ((Long) qr.query(con, sql, new ScalarHandler<>(), userId)).intValue();
		return count;
	}

	/**
	 * 查询药品的当前售价
	 * 
	 * @param userId
	 * @param mId
	 * @return
	 */
	public Medicine_Sale queryOutPrice(String userId, String mId) {
		Connection con = null;
		Medicine_Sale ms = null;
		String sql = "select outPrice from medicinal_sale where userId=? and mId=?";
		try {
			con = MyDbUtils.getConnection();
			ms = qr.query(con, sql, new BeanHandler<Medicine_Sale>(Medicine_Sale.class), userId, mId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ms;
	}

	/**
	 * 在药品售价表中插入一条记录
	 * 
	 * @param userId
	 * @param mId
	 * @param outPrice
	 */
	public void setPrice(String userId, String mId, String outPrice) {
		Connection con = null;
		String sql = "insert into medicinal_sale values(?,?,?)";
		try {
			con = MyDbUtils.getConnection();
			qr.update(con, sql, userId, mId, outPrice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 修改药品价格
	 * 
	 * @param userId
	 * @param mId
	 * @param outPrice
	 */
	public void updatePrice(String userId, String mId, String outPrice) {
		Connection con = null;
		String sql = "update medicinal_sale set outPrice=? where userId=? and mId=?";
		try {
			con = MyDbUtils.getConnection();
			qr.update(con, sql, outPrice, userId, mId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取库存分页数据
	 * 
	 * @param userId
	 * @param startIndex
	 * @param i
	 * @return
	 */
	public List<StockInfo> getPageData(String userId, int startIndex, int pageSize) {
		Connection con = null;
		List<StockInfo> list = null;
		String sql = "select mId,mName,outPrice,coalesce(sum(sNum),0) as totalNum from ( select  medicinal_stock.mId as mId,medicinal_stock.mLotNum as mLotNum,"
				+ "medicinal_stock.sNum as sNum,medicinal_base.mName as mName,medicinal_sale.outPrice as outPrice from "
				+ "medicinal_base,medicinal_stock,medicinal_sale "
				+ "where medicinal_base.mId=medicinal_stock.mId and medicinal_stock.userId= medicinal_sale.userId and"
				+ " medicinal_stock.mId= medicinal_sale.mId and medicinal_sale.userId=? )as b group by mId limit ?,?";
		try {
			con = MyDbUtils.getConnection();
			list = qr.query(con, sql, new BeanListHandler<StockInfo>(StockInfo.class), userId, startIndex, pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("list:"+list.size());
		return list;
	}

	/**
	 * 根据药品名称进行模糊查询库存信息
	 * 
	 * @param userId
	 * @param mName
	 * @return
	 */
	public List<StockInfo> getDataWithMId(String userId, String mName) {
		Connection con = null;
		List<StockInfo> list = null;
		String sql = "select mId,mName,outPrice,coalesce(sum(sNum),0) as totalNum from ( select  medicinal_stock.mId as mId,medicinal_stock.mLotNum as mLotNum,"
				+ "medicinal_stock.sNum as sNum,medicinal_base.mName as mName,medicinal_sale.outPrice as outPrice from "
				+ "medicinal_base,medicinal_stock,medicinal_sale "
				+ "where medicinal_base.mId=medicinal_stock.mId and medicinal_stock.userId= medicinal_sale.userId and"
				+ " medicinal_stock.mId= medicinal_sale.mId and medicinal_sale.userId=? and medicinal_base.mName like ? )as b group by mLotNum";
		String mName_new = "%" + mName + "%";
		try {
			con = MyDbUtils.getConnection();
			list = qr.query(con, sql, new BeanListHandler<StockInfo>(StockInfo.class), userId, mName_new);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 获取指定批次的药品的库存量
	 * 
	 * @param userId
	 * @param mId
	 * @param mLotNum
	 * @return
	 * @throws SQLException
	 */
	public int getStock(String userId, String mId, String mLotNum) {
		int num = 0;
		Connection con = null;
		String sql = "select sNum from medicinal_stock where userId=? and mId=? and mLotNum=?";
		try {
			con = MyDbUtils.getConnection();
			num = qr.query(con, sql, new ScalarHandler<>(), userId, mId, mLotNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public void addReturnBill(String userId, Return_bill rBill) {
		Connection con = null;
		String sql = "insert into returnbill values(?,?,?,?,?,?,?,?)";
		try {
			con = MyDbUtils.getCurrentConnection();
			qr.update(con, sql, userId, rBill.getReturnId(), rBill.getmId(), rBill.getmLotNum(), rBill.getrNum(),
					rBill.getrInfo(), rBill.getrTotal(), rBill.getrDate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 减少库存数量
	 * 
	 * @param userId
	 * @param rBill
	 * @throws SQLException
	 */
	public void updateStock_reduce(String userId, String mId, String mLotNum, int num) throws SQLException {
		Connection con = null;
		String sql = "update medicinal_stock set sNum=sNum-? where userId=? and mId=? and mLotNum=?";
		con = MyDbUtils.getCurrentConnection();
		qr.update(con, sql, num, userId, mId, mLotNum);
	}

	/**
	 * 添加报损记录
	 * 
	 * @param userId
	 * @param lossBill
	 */
	public void addLossBill(String userId, Frmloss_bill lossBill) {
		Connection con = null;
		String sql = "insert into reportloss values(?,?,?,?,?,?,?)";
		try {
			con = MyDbUtils.getCurrentConnection();
			qr.update(con, sql, userId, lossBill.getmId(), lossBill.getmLotNum(), lossBill.getLossNum(),
					lossBill.getLossInfo(), lossBill.getLossTotal(), lossBill.getLossDate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据药品名称进行模糊查询，查询药品信息
	 * 
	 * @param text
	 * @return
	 * @throws SQLException
	 */
	public List<Medicine_base> fuzzyQueryByMedName(String text) throws SQLException {
		List<Medicine_base> list = null;
		Connection con = null;
		String mName = "%" + text + "%";
		String sql = "select * from medicinal_base where mName like ?";
		con = MyDbUtils.getConnection();
		list = qr.query(con, sql, new BeanListHandler<Medicine_base>(Medicine_base.class), mName);
		return list;
	}

	/**
	 * 获得当前日期下指定用户的近期和过期药品总数
	 * 
	 * @param userId
	 * @param currentDate
	 * @return
	 */
	public int getNumOfOverDate(String userId, Date currentDate) {
		Connection con = null;
		String sql = "select count(*) from medicinal_stock,medicinal_lotnum where userId=? and medicinal_stock.mId=medicinal_lotnum.mId and medicinal_stock.mLotNum = medicinal_lotnum.mLotNum and datediff(medicinal_lotnum.mExp,current_date())<=30";
		int num = 0;
		try {
			con = MyDbUtils.getConnection();
			num = ((Long) qr.query(con, sql, new ScalarHandler<>(), userId)).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("num:"+num);
		return num;
	}

	/**
	 * 获得当前页的近期和过期药品的数据
	 * 
	 * @param userId
	 * @param startIndex
	 * @param i
	 * @param currentDate
	 * @return
	 * @throws SQLException 
	 */
	public List<Medicine_stock_date> getMedicineDataOver(String userId, int startIndex, int i, Date currentDate) throws SQLException {
		Connection con = null;
		List<Medicine_stock_date> list = null;
		String sql = "select medicinal_stock.mId,medicinal_stock.mLotNum,medicinal_stock.sNum,medicinal_base.mName,"
				+ "datediff(medicinal_lotnum.mExp,current_date()) as days from medicinal_base,medicinal_lotnum,medicinal_stock "
				+ "where userId=? and medicinal_base.mId = medicinal_stock.mId and medicinal_stock.mId = medicinal_lotnum.mId and medicinal_stock.mLotNum = medicinal_lotnum.mLotNum and datediff(medicinal_lotnum.mExp,current_date())<=30 limit ?,?";
		con = MyDbUtils.getConnection();
		list = qr.query(con, sql, new BeanListHandler<Medicine_stock_date>(Medicine_stock_date.class),
				userId,startIndex, i);
		System.out.println(list.size()+"size");
		return list;
	}

	// 根据药品的名称获得药品的批准文号
	public String getmIdByName(String mName) {
		Connection con = null;
		Medicine_base mb = null;
		String sql = "select mId from medicinal_base where mName=?";
		try {
			con = MyDbUtils.getConnection();
			mb = qr.query(con, sql, new BeanHandler<Medicine_base>(Medicine_base.class), mName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mb.getmId();
	}

	/**
	 * 根据药品批准文号查询当前用户的该药品所有库存信息
	 * 
	 * @param userId
	 * @param mName
	 * @return
	 * @throws SQLException
	 */
	public List<Medicine_stock> getMedicineStock(String userId, String mId) throws SQLException {
		Connection con = null;
		List<Medicine_stock> list = null;
		String sql = "select * from medicinal_stock where userId=? and mId=?";
		con = MyDbUtils.getCurrentConnection();
		list = qr.query(con, sql, new BeanListHandler<Medicine_stock>(Medicine_stock.class), userId, mId);
		return list;
	}

	/**
	 * 根据起始日期查询该用户的药品销售情况
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getMeddicineSales(String userId, Date startDate, Date endDate)throws SQLException {
		List<Map<String, Object>> list = null;
		Connection con = null;
		String sql = "select mName,sum(num) as sale_num from medicinal_sale_num where userId=? and issue_date between ? and ? group by mName";
		con = MyDbUtils.getCurrentConnection();
		list = qr.query(con, sql, new MapListHandler(), userId, startDate, endDate);
		return list;
	}

	/**
	 * 获得指定日期内用户进货总支出
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public double getPurTotalExpenses(String userId, Date startDate, Date endDate) throws SQLException {
		Connection con = null;
		double pur_totalExpenses = 0;
		String sql = "select coalesce(sum(total),0) as pur_totalExpenses from purchase_bill where userId=? and enterDate between ? and ?";
		con = MyDbUtils.getCurrentConnection();
		pur_totalExpenses = (double) qr.query(con, sql, new ScalarHandler(), userId, startDate, endDate);
		System.out.println(pur_totalExpenses + "1111111111111");
		return pur_totalExpenses;
	}

	/**
	 * 获得指定日期范围内用户药品耗损的总费用
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public double getFrmlossTotalExpenses(String userId, Date startDate, Date endDate) throws SQLException {
		Connection con = null;
		double frmloss_totalExpenses = 0;
		String sql = "select coalesce(sum(lossTotal),0) as frmloss_totalExpenses from reportloss where userId=? and lossDate between ? and ?";
		con = MyDbUtils.getCurrentConnection();
		frmloss_totalExpenses = (double) qr.query(con, sql, new ScalarHandler(), userId, startDate, endDate);
		return frmloss_totalExpenses;
	}

	/**
	 * 获得指定日期范围内药品退货的总费用
	 * 
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public double getReturnTotalExpenses(String userId, Date startDate, Date endDate) throws SQLException {
		Connection con = null;
		double return_totalExpenses = 0;
		String sql = "select coalesce(sum(rTotal),0) as return_totalExpenses from returnbill where userId=? and rDate between ? and ?";
		con = MyDbUtils.getCurrentConnection();
		return_totalExpenses = (double) qr.query(con, sql, new ScalarHandler(), userId, startDate, endDate);
		return return_totalExpenses;
	}
}
