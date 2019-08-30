package domain;
/**
 * 药品总库存信息显示数据持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月5日上午11:06:48
   @package_name domain
	@file_name StockInfo.java
 */

public class StockInfo {
	private String mId;//药品批准文号
	private String mName;//药品名称
	private int totalNum;//库存量
	private double outPrice;//药品售价
	
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
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(double outPrice) {
		this.outPrice = outPrice;
	}
	@Override
	public String toString() {
		return "StockInfo [mId=" + mId + ", mName=" + mName + ", totalNum=" + totalNum + ", outPrice=" + outPrice + "]";
	}
	
	

}
