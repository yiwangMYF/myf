package domain;

import java.sql.Date;

/**
 * ҩƷ��Ϣ�־û���
 * @author ë���
 * @caeateTime 2019��4��23������9:30:25
   @package_name domain
	@file_name Medicine.java
 */

public class Medicine {
	private String mId;//ҩƷ��׼�ĺ�
	private String mName;//ҩƷ����
	private String mLotNum;//ҩƷ��������
	private String mSpec;//ҩƷ���
	private String mCateGory;//ҩƷ���
	private Date mPd;//ҩƷ��������
	private Date mExp;//�����Ч����
	private String supId;//��Ӧ��id
	
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
