package domain;
/**
 * ҩƷ�ܿ����Ϣ��ʾ���ݳ־û���
 * @author ë���
 * @caeateTime 2019��5��5������11:06:48
   @package_name domain
	@file_name StockInfo.java
 */

public class StockInfo {
	private String mId;//ҩƷ��׼�ĺ�
	private String mName;//ҩƷ����
	private int totalNum;//�����
	private double outPrice;//ҩƷ�ۼ�
	
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
