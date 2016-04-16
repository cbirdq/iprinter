package com.printer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable {
	//默认每页显示的记录数
	private static int DEFAULT_PAGE_SIZE = 20; 
	
	//每页显示的记录数
	private int pageSize = DEFAULT_PAGE_SIZE;
	
	//当前页面第一条数据在List中的位置，从0开始
	private long start;
	
	//当前页面中存放的记录，类型一般为List
	private List data;
	
	//总的记录数
	private long totalCount;
	
	
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}
	
	public Page(long start, long totalCount, int pageSize, List data) {
		this.start = start;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.data = data;
	}
	
	//获取总页数
	public long getTotalPageCount() {
		if(totalCount % pageSize == 0) 
			return totalCount / pageSize;
		else 
			return totalCount / pageSize + 1;
	}
	
	//获取当前的页码，从1开始
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}
	
	//判断当前页是否有下一页
	public boolean isHasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}
	
	//判断当前页是否有上一页
	public boolean isHasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}
	
	//获取任意一页第一条数据在数据集中的位置，每页条数使用默认值
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}
	
	//获取任意一页第一条数据在数据集中的位置
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
	
	
	
	
	
	
}
