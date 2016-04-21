package com.printer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
 * Servlet implementation class PriceServlet
 */
public class PriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PriceService service = new PriceService(new PriceDao());
		JSONObject jsonObject = new JSONObject();
		
		String action = request.getParameter("action");
		System.out.println(action);
		//获取纸张大小
		if(action != null && action.equals("list-papersize")) {
			List<String> paperSizeList = service.getAllPaperSize();
			jsonObject.put("paperSizeList", paperSizeList);
			
			//查询特定entryid所对应的文件名和Price对象，返回给前端展示
			String entryid = request.getParameter("entryid");
			Entry theEntry = EntryManager.getEntry(request, entryid);
			
			jsonObject.put("originalname", theEntry.getFile().getOriginalname());
			
			Price price = theEntry.getPrice();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", price.getId());
			map.put("biside", price.getBiside());
			map.put("color", price.getColor());
			map.put("price", price.getPrice());
			map.put("papersize", price.getPapersize());
			
			jsonObject.put("price", map);
			
		} else if(action != null && action.equals("get-price")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("papersize", request.getParameter("papersize"));
			map.put("biside", Integer.parseInt(request.getParameter("biside")));
			map.put("color", Integer.parseInt(request.getParameter("color")));
			float price = service.getPriceBy(map).get(0);
			jsonObject.put("price", price);
		}
		
		System.out.println(jsonObject.toString());
		response.getWriter().print(jsonObject.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
