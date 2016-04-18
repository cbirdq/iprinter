package com.printer.dao.impl;

import java.util.List;
import java.util.Map;

import com.printer.dao.BaseDao;
import com.printer.model.Order;
import com.printer.model.Page;

public class OrderDao extends BaseDao<Order, String> {
	
	private static String GET_BY_MULTI = "From Order where 1=1 ";
	
	private static String GET_BY_SHOPID = "From Order where shopid=? ";
	
	/**
	 * 查找某个特定店铺的所有订单
	 * @return
	 */
	public List<Order> getOrderByShopid(String shopid) {
		return (List<Order>)find(GET_BY_SHOPID, new String[]{shopid});
	}
	
	
	/**
	 * 分页查找某个特定店铺的订单
	 * @param shopid
	 * @param page
	 * @return
	 */
	public Page getPagedOrderByShopid(String shopid, int pageNo, int pageSize) {
		return super.pagedQuery(GET_BY_SHOPID, pageNo, pageSize, new String[]{shopid});
	}
	
	/**
	 * 分页多条件查找订单
	 * @param shopid
	 * @param page
	 * @return
	 */
	public Page getPagedOrder(Map<String, Object> constraints, int pageNo, int pageSize) {
		StringBuffer hqlsb = new StringBuffer(GET_BY_MULTI);
		Object[] args = new Object[constraints.size()];
		int i = 0;
		for(String key: constraints.keySet()) {
			args[i++] = constraints.get(key);
			hqlsb.append("and " + key + "=?");
		}
		
		return super.pagedQuery(hqlsb.toString(), pageNo, pageSize, args);
	}
	
	
}
