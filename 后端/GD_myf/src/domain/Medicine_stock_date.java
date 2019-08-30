package domain;
/**
 * 用于存储药品的id,lotNUm,name,数量和剩余有效时间（天数）信息的持久化
 * @author 毛燕丰
 * @caeateTime 2019年5月8日上午10:31:29
   @package_name domain
	@file_name Medicine_stock_date.java
 */

public class Medicine_stock_date {
	private String mId;//药品批准文号
	private String mLotNum;//药品生产批号
	private String mName;//药品名称
	private int sNum;//药品库存数量
	private int days;//药品剩余有效时间
	
	
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
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getsNum() {
		return sNum;
	}
	public void setsNum(int sNum) {
		this.sNum = sNum;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	
	@Override
	public String toString() {
		return "Medicine_stock_date [mId=" + mId + ", mLotNum=" + mLotNum + ", mName=" + mName + ", sNum=" + sNum
				+ ", days=" + days + "]";
	}
	
	
}
