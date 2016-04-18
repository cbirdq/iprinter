package com.printer.service.impl;

import java.util.List;
import java.util.Map;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.OrderDao;
import com.printer.model.Order;
import com.printer.model.Page;
import com.printer.service.BaseService;

public class OrderService extends BaseService<Order, String> {
	
	public OrderService(BaseDao baseDao) {
		super(baseDao);
	}
	
	
	

	public List<Order> getOrderByShopid(String shopid) {
		return ((OrderDao)getBaseDao()).getOrderByShopid(shopid);
	}
	
	
	public Page getPagedOrderByShopid(String shopid, int pageNo, int pageSize) {
		return ((OrderDao)getBaseDao()).getPagedOrderByShopid(shopid, pageNo, pageSize);
	}
	
	
	public Page getPagedOrder(Map<String, Object> constraints, int pageNo, int pageSize) {
		return ((OrderDao)getBaseDao()).getPagedOrder(constraints, pageNo, pageSize);
	}
	
	
	
	

}
