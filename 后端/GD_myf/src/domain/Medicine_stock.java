package domain;
/**
 * ����ҩƷ������ݳ־û���
 * @author ë���
 * @caeateTime 2019��5��16������8:16:54
   @package_name domain
	@file_name Medicine_stock.java
 */

public class Medicine_stock {
	private String userId;
	private String mId;
	private String mLotNum;
	private int sNum;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public int getsNum() {
		return sNum;
	}
	public void setsNum(int sNum) {
		this.sNum = sNum;
	}
	@Override
	public String toString() {
		return "Medicine_stock [userId=" + userId + ", mId=" + mId + ", mLotNum=" + mLotNum + ", sNum=" + sNum + "]";
	}
	
	

}
