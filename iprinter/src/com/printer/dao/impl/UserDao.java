package com.printer.dao.impl;

import com.printer.dao.BaseDao;
import com.printer.model.User;


public class UserDao extends BaseDao<User, String> {
	
	private static String GET_BY_OPENID_TOKEN = "From User where openid=? and token=?";
	
	/**
	 * 根据openid和token查找用户 
	 * @return
	 */
	public User getUserByOpenidAndToken(String openid, String token) {
		Object[] args = new Object[] {openid, token};	
		return (User) findOne(GET_BY_OPENID_TOKEN, args);
	}
	
	
}
