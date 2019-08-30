package domain;

import java.sql.Date;

/**
 * 药品信息持久化类
 * @author 毛燕丰
 * @caeateTime 2019年4月23日上午9:30:25
   @package_name domain
	@file_name Medicine.java
 */

public class Medicine {
	private String mId;//药品批准文号
	private String mName;//药品名称
	private String mLotNum;//药品生产批号
	private String mSpec;//药品规格
	private String mCateGory;//药品类别
	private Date mPd;//药品生产日期
	private Date mExp;//最后有效日期
	private String supId;//供应商id
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmLotNum() {
		return mLotNum;
	}
	public void setmLotNum(String mLotNum) {
		this.mLotNum = mLotNum;
	}
	public String getmSpec() {
		return mSpec;
	}
	public void setmSpec(String mSpec) {
		this.mSpec = mSpec;
	}
	public Date getmPd() {
		return mPd;
	}
	public void setmPd(Date mPd) {
		this.mPd = mPd;
	}
	public Date getmExp() {
		return mExp;
	}
	public void setmExp(Date mExp) {
		this.mExp = mExp;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getmCateGory() {
		return mCateGory;
	}
	public void setmCateGory(String mCateGory) {
		this.mCateGory = mCateGory;
	}
	@Override
	public String toString() {
		return "Medicine [mId=" + mId + ", mName=" + mName + ", mLotNum=" + mLotNum + ", mSpec=" + mSpec
				+ ", mCateGory=" + mCateGory + ", mPd=" + mPd + ", mExp=" + mExp + ", supId=" + supId + "]";
	}
	
	
	
	

}
