package domain;

import java.sql.Date;

/**
 * ������Ϣ�־û���
 * @author ë���
 * @caeateTime 2019��5��7������7:07:28
   @package_name domain
	@file_name Frmloss_bill.java
 */

public class Frmloss_bill {
	private String mId;//ҩƷ��׼�ĺ�
	private String mLotNum;//ҩƷ��������
	private int lossNum;//��������
	private String lossInfo;//������Ϣ
	private double lossTotal;//�����ܽ��
	private Date lossDate;//���������
	
	
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
