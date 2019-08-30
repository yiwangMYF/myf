package domain;
/**
 * 药品基本信息持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月7日下午10:04:28
   @package_name domain
	@file_name Medicine_base.java
 */

public class Medicine_base {
	private String mId;//药品批准文号
	private String mName;//药品名称
	private String mSpec;//药品规格
	private String mCateGory;//药品类别
	private String supId;//供应商id
	
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
