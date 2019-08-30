package domain;

import java.sql.Date;

/**
 * 处方信息数据持久化类
 * @author 毛燕丰
 * @caeateTime 2019年5月15日上午7:22:39
   @package_name domain
	@file_name Prescription.java
 */

public class Prescription {
	private String pId;
	private String pName;
	private String pSex;
	private Date pBirth;
	private String clinical_diagnosis;
	private String med_details;
	private double cost;
	private String userId;
	private Date issue_date;
	
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
	public String getClinical_diagnosis() {
		return clinical_diagnosis;
	}
	public void setClinical_diagnosis(String clinical_diagnosis) {
		this.clinical_diagnosis = clinical_diagnosis;
	}
	public String getMed_details() {
		return med_details;
	}
	public void setMed_details(String med_details) {
		this.med_details = med_details;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}
	
	@Override
	public String toString() {
		return "Prescription [pId=" + pId + ", pName=" + pName + ", pSex=" + pSex + ", pBirth=" + pBirth
				+ ", clinical_diagnosis=" + clinical_diagnosis + ", med_details=" + med_details + ", cost=" + cost
				+ ", userId=" + userId + ", issue_date=" + issue_date + "]";
	}
	
	

}
