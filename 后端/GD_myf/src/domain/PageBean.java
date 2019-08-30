package domain;

import java.util.List;

/**
 * 数据分页类
 * @author 毛燕丰
 * @caeateTime 2019年5月5日上午10:53:29
   @package_name domain
	@file_name PageBean.java
 */

public class PageBean<T>{
	private int currentPage;//当前页
	private int totalRecord;//总记录数
	private int pageSize=8;//每页显示条数
	private int totalPages;//总页数
	private List<T> list;//数据集合	
	
	public PageBean(int currentPage, int totalRecord, List<T> list) {
	
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.list = list;
		this.totalPages=this.getTotalPages();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return (int) Math.ceil(1.0*this.totalRecord/this.pageSize);
	}
	public void setTotalPages() {
		this.totalPages = (int) Math.ceil(1.0*this.totalRecord/this.pageSize);
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	

}
