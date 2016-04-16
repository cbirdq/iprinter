package com.printer.dao.impl;

import java.util.List;

import com.printer.dao.BaseDao;
import com.printer.model.Merchant;



public class MerchantDao extends BaseDao<Merchant, String> {
	
	private static String GET_BY_NAME_PASSWORD = "From Merchant where name=? and password=?";
	
	/**
	 * 查找用户名和密码
	 * @return
	 */
	public Merchant getMerchantByNamePassword(String username, String password) {
		Object[] args = new Object[] {username, password};	
		return (Merchant) find(GET_BY_NAME_PASSWORD, args);
	}
	
	
	
	
}
