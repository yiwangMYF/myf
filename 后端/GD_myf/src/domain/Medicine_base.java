package domain;
/**
 * ҩƷ������Ϣ�־û���
 * @author ë���
 * @caeateTime 2019��5��7������10:04:28
   @package_name domain
	@file_name Medicine_base.java
 */

public class Medicine_base {
	private String mId;//ҩƷ��׼�ĺ�
	private String mName;//ҩƷ����
	private String mSpec;//ҩƷ���
	private String mCateGory;//ҩƷ���
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
	public String getmSpec() {
		return mSpec;
	}
	public void setmSpec(String mSpec) {
		this.mSpec = mSpec;
	}
	public String getmCateGory() {
		return mCateGory;
	}
	public void setmCateGory(String mCateGory) {
		this.mCateGory = mCateGory;
	}
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	@Override
	public String toString() {
		return "Medicine_base [mId=" + mId + ", mName=" + mName + ", mSpec=" + mSpec + ", mCateGory=" + mCateGory
				+ ", supId=" + supId + "]";
	}
	
	
}
