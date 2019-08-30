package domain;

import java.sql.Date;

/**
 * 报损信息持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月7日下午7:07:28
   @package_name domain
	@file_name Frmloss_bill.java
 */

public class Frmloss_bill {
	private String mId;//药品批准文号
	private String mLotNum;//药品生产批号
	private int lossNum;//报损数量
	private String lossInfo;//报损信息
	private double lossTotal;//报损总金额
	private Date lossDate;//报损的日期
	
	
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
	public int getLossNum() {
		return lossNum;
	}
	public void setLossNum(int lossNum) {
		this.lossNum = lossNum;
	}
	public String getLossInfo() {
		return lossInfo;
	}
	public void setLossInfo(String lossInfo) {
		this.lossInfo = lossInfo;
	}
	public double getLossTotal() {
		return lossTotal;
	}
	public void setLossTotal(double lossTotal) {
		this.lossTotal = lossTotal;
	}
	public Date getLossDate() {
		return lossDate;
	}
	public void setLossDate(Date lossDate) {
		this.lossDate = lossDate;
	}
	@Override
	public String toString() {
		return "Frmloss_bill [mId=" + mId + ", mLotNum=" + mLotNum + ", lossNum=" + lossNum + ", lossInfo=" + lossInfo
				+ ", lossTotal=" + lossTotal + ", lossDate=" + lossDate + "]";
	}
	
	
	

}
