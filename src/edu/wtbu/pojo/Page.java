package edu.wtbu.pojo;

public class Page {
	int total;
	int pageSize;
	int startPage;
	public Page() {
		// TODO Auto-generated constructor stub
	}
	public Page(int total, int pageSize, int startPage) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.startPage = startPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
}
