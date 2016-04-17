package com.printer.util;

import javax.servlet.http.HttpServletRequest;

import com.printer.model.BaseUser;


/**
 * 用户会话管理类
 * @author ThinkPad
 *
 */
public class UserManager {
	
	
	/**
	 * 将用户加入session中
	 * @param quest
	 * @param baseUser
	 */
	public static void saveUser(HttpServletRequest request, BaseUser user) {
		request.getSession().setAttribute("user", user);
	}
	
	
	/**
	 * 从session中获取用户信息
	 * @param request
	 * @return
	 */
	public static BaseUser getUser(HttpServletRequest request) {
		BaseUser user = null;
		if(request.getSession().getAttribute("user") != null) {
			user = (BaseUser) request.getSession().getAttribute("user");
		}
		
		return user;
	}
	
	/**
	 * 判断用户是否登录
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		return getUser(request) != null;
	}
	
	
}
