package domain;

import java.util.List;

/**
 * ���ݷ�ҳ��
 * @author ë���
 * @caeateTime 2019��5��5������10:53:29
   @package_name domain
	@file_name PageBean.java
 */

public class PageBean<T>{
	private int currentPage;//��ǰҳ
	private int totalRecord;//�ܼ�¼��
	private int pageSize=8;//ÿҳ��ʾ����
	private int totalPages;//��ҳ��
	private List<T> list;//���ݼ���	
	
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
