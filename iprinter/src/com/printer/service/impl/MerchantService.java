package com.printer.service.impl;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.MerchantDao;
import com.printer.exception.UserNotFoundException;
import com.printer.model.Merchant;
import com.printer.service.BaseService;

public class MerchantService extends BaseService<Merchant, String> {
    
	public MerchantService(BaseDao baseDao) {
		super(baseDao);	
	}

	/**
	 * 根据用户名密码获取用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	public Merchant getMerchantByNamePassword(String username, String password) 
			throws UserNotFoundException {
		Merchant merchant = ((MerchantDao)getBaseDao()).getMerchantByNamePassword(username, password);
		if(merchant == null)
			throw new UserNotFoundException("用户不存在！");
		
		return merchant;
	}
	
	
	
}
