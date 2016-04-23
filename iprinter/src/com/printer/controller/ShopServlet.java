package com.printer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.printer.dao.impl.ShopDao;
import com.printer.model.Shop;
import com.printer.model.User;
import com.printer.service.impl.ShopService;

/**
 * Servlet implementation class ShopServlet
 */
public class ShopServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		//获取用户所在学校附近的打印店
		if(action != null && action.equals("list-nearby-shop")) {
			User u = (User) UserManager.getUser(request);
			int schoolid = u.getSchool().getId();
			
			ShopService service = new ShopService(new ShopDao());
			List<Shop> shopList = service.getShopBySchoolid(schoolid);
			JSONObject json = new JSONObject();
			json.put("shopList", shopList);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json.toString());
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
