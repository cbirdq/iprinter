package com.printer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.MerchantDao;
import com.printer.exception.UserNotFoundException;
import com.printer.model.Merchant;
import com.printer.service.impl.MerchantService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("username");  
		String password=request.getParameter("userpassword");
		 
		MerchantService merchantService = new MerchantService(new MerchantDao());
		Merchant merchant = null;
		try {
			merchant = merchantService.getMerchantByNamePassword(name, password);
			if(merchant.getUsername().equals(name) && merchant.getPassword().equals(password)) {
				//用户登录成功，进入系统首页
				HttpSession session = request.getSession();//传入session值
				session.setAttribute("merchant", merchant);
				response.sendRedirect("/index.html");
			}
		} catch(UserNotFoundException e) {
			//如果用户不存在，则返回登录页面
			request.setAttribute("message", "登录失败");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		 }
		 
	}

}
