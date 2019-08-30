package domain;

import java.sql.Date;

/**
 * ������Ϣ�־û���
 * @author ë���
 * @caeateTime 2019��5��15������8:34:33
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
