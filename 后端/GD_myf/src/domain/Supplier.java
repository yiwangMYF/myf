package domain;
/**
 * 供应商数据持久化类
 * @author 毛燕丰
 * @caeateTime 2019年4月23日上午9:48:23
   @package_name domain
	@file_name Supplier.java
 */

public class Supplier {
	private String supId;//供应商id（统一社会信用代码）
	private String supName;//供应商名称
	private String supAddress;//供应商地址
	private String supTel;//供应商联系电话
	public String getSupId() {
		return supId;
	}
	public void setSupId(String supId) {
		this.supId = supId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getSupAddress() {
		return supAddress;
	}
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}
	public String getSupTel() {
		return supTel;
	}
	public void setSupTel(String supTel) {
		this.supTel = supTel;
	}
	
	
	
	

}
