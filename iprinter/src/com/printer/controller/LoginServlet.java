package com.printer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.printer.dao.impl.MerchantDao;
import com.printer.dao.impl.UserDao;
import com.printer.exception.RegisterFailedException;
import com.printer.exception.UserNotFoundException;
import com.printer.model.Merchant;
import com.printer.model.User;
import com.printer.service.impl.MerchantService;
import com.printer.service.impl.UserService;
import com.printer.util.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		// 获取用户的登录状态
		if(action != null && action.equals("check_login")) {
			JSONObject jsonObject = new JSONObject();
			if(UserManager.isLogin(request)) {
				jsonObject.put("status", "login");
			} else {
				jsonObject.put("status", "logout");
			}
			response.getWriter().print(jsonObject.toString());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//qq登录
		if(action != null && action.equals("qc_login")) {
			//获得前端的参数
			String openid = request.getParameter("openid");
			String token = request.getParameter("accesstoken");
			
			UserService userService = new UserService(new UserDao());
			User user;
			HttpSession session = request.getSession();
			try {
				user = userService.getUserByOpenidAndToken(openid, token);
				//用户登录成功后，将用户信息（openid, token等信息）注入session中
				UserManager.saveUser(request, user);
				
			} catch (UserNotFoundException e) {
				e.printStackTrace();
				//用户不存在，注册到数据库中
				try {
					user = userService.register(openid, token);
					UserManager.saveUser(request, user);
				} catch (RegisterFailedException e1) {
					e1.printStackTrace();
				}
			}
			
		} 
		//打印店登陆
		else {
		
			String name=request.getParameter("username");  
			String password=request.getParameter("userpassword");
			 
			MerchantService merchantService = new MerchantService(new MerchantDao());
			Merchant user = null;
			try {
				user = merchantService.getMerchantByNamePassword(name, password);
				//用户登录成功，进入系统首页
				HttpSession session = request.getSession();//传入session值
				UserManager.saveUser(request, user);
				response.sendRedirect("/sidebar.jsp");
				
			} catch(UserNotFoundException e) {
				//如果用户不存在，则返回登录页面
				request.setAttribute("message", "登录失败");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		 
	}

}
