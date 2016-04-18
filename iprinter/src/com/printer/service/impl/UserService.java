package com.printer.service.impl;

import org.json.JSONObject;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.UserDao;
import com.printer.exception.RegisterFailedException;
import com.printer.exception.UserNotFoundException;
import com.printer.model.User;
import com.printer.service.BaseService;
import com.printer.util.UuidGenerator;

public class UserService extends BaseService<User, String> {

	public UserService(BaseDao baseDao) {
		super(baseDao);
	}

	/**
	 * 注册qc openid, accesstoken，第一次通过qq第三方接口登录时调用该函数
	 * @param openid
	 * @param token
	 * @throws RegisterFailedException 
	 */
	public User register(String openid, String token) 
			throws RegisterFailedException {
		User u = new User();
		u.setId(UuidGenerator.getUUID());
		u.setOpenid(openid);
		u.setToken(token);
		if(super.save(u) == null)
			throw new RegisterFailedException("qc注册失败！");
		return u;
	}
	
	
	/**
	 * 根据openid和token查找用户 
	 * @param openid
	 * @param token
	 * @return
	 * @throws UserNotFoundException 
	 */
	public User getUserByOpenidAndToken(String openid, String token) 
			throws UserNotFoundException {
		User u = ((UserDao)getBaseDao()).getUserByOpenidAndToken(openid, token);
		if(u == null)
			throw new UserNotFoundException("用户不存在！");
		return u;
	}
	
	
	/**
	 * 单元测试
	 * @param args
	 */
	public static void main(String[] args) {
		/*UserService us = new UserService(new UserDao());
		User u = null;
		try {
			u = us.register("01", "14");
		} catch (RegisterFailedException e) {
			System.out.println(u.getId() + ", " + u.getOpenid());
			e.printStackTrace();
		}
		
		
		try {
			u = us.getUserByOpenidAndToken("01", "14");
			System.out.println(u.getId() + ", " + u.getOpenid());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
	}
}
