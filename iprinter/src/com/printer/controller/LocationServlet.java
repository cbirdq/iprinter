package com.printer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.printer.dao.impl.SchoolDao;
import com.printer.model.School;
import com.printer.model.User;
import com.printer.service.BaseService;
import com.printer.service.impl.LocationService;

public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LocationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		BaseService service = new LocationService(new SchoolDao());
		if(action != null && action.equals("list-all")) {
			List<School> schools = service.listAll();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("schools", schools);
			jsonObject.put("size", schools.size());
			int checkedItem = 0;
			if(request.getSession().getAttribute("user") != null ) {
				User user =(User) request.getSession().getAttribute("user");
				checkedItem = (user.getSchool() == null ? 0 : user.getSchool().getId());
			}
			
			jsonObject.put("checked", checkedItem);
			
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(jsonObject.toString());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	
	
	
	
	

}
