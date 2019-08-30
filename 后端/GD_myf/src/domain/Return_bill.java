package domain;

import java.sql.Date;

/**
 * 退货单数据持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月7日下午12:41:54
   @package_name domain
	@file_name Return_bill.java
 */

public class Return_bill {
	private String returnId;//退单号
	private String mId;//药品批准文号
	private String mLotNum;//药品生产批号
	private int rNum;//退货数量
	private String rInfo;//退货详情
	private double rTotal;//退货总额
	private Date rDate;//退货订单日期
	
	public String getReturnId() {
		return returnId;
	}
	public void setReturnId(String returnId) {
		this.returnId = returnId;
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
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getrInfo() {
		return rInfo;
	}
	public void setrInfo(String rInfo) {
		this.rInfo = rInfo;
	}
	public double getrTotal() {
		return rTotal;
	}
	public void setrTotal(double rTotal) {
		this.rTotal = rTotal;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	@Override
	public String toString() {
		return "Return_bill [returnId=" + returnId + ", mId=" + mId + ", mLotNum=" + mLotNum + ", rNum=" + rNum
				+ ", rInfo=" + rInfo + ", rTotal=" + rTotal + ", rDate=" + rDate + "]";
	}
	
	
}
