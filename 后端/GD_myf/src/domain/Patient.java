package domain;

import java.sql.Date;

/**
 * 患者信息持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月15日上午8:34:33
   @package_name domain
	@file_name Patient.java
 */

public class Patient {
	private String pId;
	private String pName;
	private String pSex;
	private Date pBirth;
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpSex() {
		return pSex;
	}
	public void setpSex(String pSex) {
		this.pSex = pSex;
	}
	public Date getpBirth() {
		return pBirth;
	}
	public void setpBirth(Date pBirth) {
		this.pBirth = pBirth;
	}
	@Override
	public String toString() {
		return "Patient [pId=" + pId + ", pName=" + pName + ", pSex=" + pSex + ", pBirth=" + pBirth + "]";
	}
	
	

}
