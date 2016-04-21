package com.printer.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.printer.dao.impl.PriceDao;
import com.printer.model.Entry;
import com.printer.model.Price;
import com.printer.service.impl.PriceService;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		JSONObject jo = new JSONObject();
		
		if(action != null && action.equals("get-session-entries")) {
			Collection<Entry> entries = EntryManager.getEntries(request).values();
			jo.put("entries", entries);
		}
		
		System.out.println(jo.toString());
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(jo.toString());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		//单个条目的打印设置
		if(action != null && action.equals("set_entry")) {
			//获取参数
			String entryid = request.getParameter("entryid");
			int printcount = Integer.parseInt(request.getParameter("printcount"));
			
			String papersize = request.getParameter("papersize");
			int biside = Integer.parseInt(request.getParameter("biside"));
			int color = Integer.parseInt(request.getParameter("color"));
			float price = Integer.parseInt(request.getParameter("price"));
			
			//从session中获取对应的Entry对象
			Entry theEntry = EntryManager.getEntry(request, entryid);
			theEntry.setPrintcount(printcount); //设置打印数量
			theEntry.setMoney(price * printcount); //设置金额
			
			PriceService priceService = new PriceService(new PriceDao());
			Price priceObj = priceService.getBy(papersize, biside, color);
			theEntry.setPrice(priceObj);//设置价格对象属性
			JSONObject jo = new JSONObject();
			jo.put("status", "ok");
			response.getWriter().print(jo.toString());
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0 ; i < 10; i++)
			map.put("a" + i, i);
		
		Collection<Integer> values = map.values();
		JSONObject js = new JSONObject();
		js.put("int", values);
		System.out.println(js.toString());
		
	}
	
	

}
