package com.printer.dao.impl;

import java.util.List;

import com.printer.dao.BaseDao;
import com.printer.model.Merchant;
import com.printer.model.Order;
//import com.printer.model.Orders;

public class OrderDao extends BaseDao<Order, String>{
 private static String GET_BY_shopid = "From Order where shopid=? ";
	
	/**
	 * 查找用户名和密码
	 * @return
	 */
	public List<Order> getMerchantByNamePassword(String shopid) {
		Object[] args = new Object[] {shopid};	
		return (List<Order>)find(GET_BY_shopid,args);
	}
	
}
