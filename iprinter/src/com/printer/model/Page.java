package com.printer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {
	//默认每页显示的记录数
	private static int DEFAULT_PAGE_SIZE = 20; 
	
	//每页显示的记录数
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	//当前页数
	private long pageNo;
	
	//当前页面第一条数据在List中的位置，从0开始
	private long start;
	
	//当前页面中存放的记录，类型一般为List
	private List data;
	
	//总的记录数
	private long totalCount;

	
	public Page() {
		this(0, DEFAULT_PAGE_SIZE, new ArrayList());
		this.totalCount = 0;
	}
	
	public Page(long pageNo) {
		this.pageNo = pageNo;
		this.start = (pageNo - 1) * pageSize;
	}
	public Page(long pageNo,int pageSize){
		this.pageNo=pageNo;
		this.pageSize=pageSize;		
		this.start = (pageNo - 1) * pageSize;
	}
	public Page(long pageNo, int pageSize, List data) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.start = (pageNo - 1) * pageSize;
		this.data = data;
	}
	
	
	
	//获取总页数
	public long getTotalPageCount() {
		if(totalCount % pageSize == 0) 
			return totalCount / pageSize;
		else 
			return totalCount / pageSize + 1;
	}
	
	//设置总记录数
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	//获取当前的页码，从1开始
	public long getPageNo() {
		return pageNo;
	}
	
	//判断当前页是否有下一页
	public boolean isHasNextPage() {
		return this.getPageNo() < this.getTotalPageCount();
	}
	
	//判断当前页是否有上一页
	public boolean isHasPreviousPage() {
		return this.getPageNo() > 1;
	}
	
	public long getStart() {
		return this.start;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}
	
	
	public void setPageData(List pageData) {
		this.data = pageData;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	
	
	
}
