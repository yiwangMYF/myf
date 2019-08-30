package domain;

import java.sql.Date;

/**
 * 进货订单数据持久化类
 * @author 毛燕丰
 * @caeateTime 2019年4月23日上午9:56:15
   @package_name domain
	@file_name Purchase_bill.java
 */

public class Purchase_bill {
	private String purId;//进货单id
	private String mId;//药品批准文号
	private String mLotNum;//药品生产批号
	private double purPrice;//药品进货单价
	private int purNum;//药品进购数量
	private Date enterDate;//进货日期
	private double total;//进货的药品总价
	
	
	public Purchase_bill(String purId, String mId, String mLotNum, double purPrice, int purNum, Date enterDate) {
		super();
		this.purId = purId;
		this.mId = mId;
		this.mLotNum = mLotNum;
		this.purPrice = purPrice;
		this.purNum = purNum;
		this.enterDate = enterDate;
		this.total=this.purPrice*this.purNum;
	}
	public Purchase_bill() {
		// TODO Auto-generated constructor stub
	}
	public String getpurId() {
		return purId;
	}
	public void setpurId(String purId) {
		this.purId = purId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmLotNum() {
		return mLotNum;
	}
	public void setmLotNum(String mLotNum) {
		this.mLotNum = mLotNum;
	}
	public double getpurPrice() {
		return purPrice;
	}
	public void setpurPrice(double purPrice) {
		this.purPrice = purPrice;
	}
	public int getPurNum() {
		return purNum;
	}
	public void setPurNum(int purNum) {
		this.purNum = purNum;
	}
	public Date getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	
	public double getTotal() {
		return purPrice*purNum;
	}
	public void setTotal() {
		this.total=this.purPrice*this.purNum;
	}
	@Override
	public String toString() {
		return "Purchase_bill [purId=" + purId + ", mId=" + mId + ", mLotNum=" + mLotNum + ", purPrice="
				+ purPrice + ", purNum=" + purNum + ", enterDate=" + enterDate + ", total=" + total + "]";
	}
	

	
	

}
