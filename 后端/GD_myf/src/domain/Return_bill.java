package domain;

import java.sql.Date;

/**
 * �˻������ݳ־û���
 * @author ë���
 * @caeateTime 2019��5��7������12:41:54
   @package_name domain
	@file_name Return_bill.java
 */

public class Return_bill {
	private String returnId;//�˵���
	private String mId;//ҩƷ��׼�ĺ�
	private String mLotNum;//ҩƷ��������
	private int rNum;//�˻�����
	private String rInfo;//�˻�����
	private double rTotal;//�˻��ܶ�
	private Date rDate;//�˻���������
	
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
