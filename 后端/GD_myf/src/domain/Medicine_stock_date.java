package domain;
/**
 * ���ڴ洢ҩƷ��id,lotNUm,name,������ʣ����Чʱ�䣨��������Ϣ�ĳ־û�
 * @author ë���
 * @caeateTime 2019��5��8������10:31:29
   @package_name domain
	@file_name Medicine_stock_date.java
 */

public class Medicine_stock_date {
	private String mId;//ҩƷ��׼�ĺ�
	private String mLotNum;//ҩƷ��������
	private String mName;//ҩƷ����
	private int sNum;//ҩƷ�������
	private int days;//ҩƷʣ����Чʱ��
	
	
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
