package domain;
/**
 * 药品售价信息持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月5日下午1:21:29
   @package_name domain
	@file_name Medicine_Sale.java
 */

public class Medicine_Sale {
	private String userId;//用户id
	private String mId;//药品批准文号
	private double outPrice;//药品售价
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
	public double getOutPrice() {
		return outPrice;
	}
	public void setOutPrice(double outPrice) {
		this.outPrice = outPrice;
	}
	
	

}
