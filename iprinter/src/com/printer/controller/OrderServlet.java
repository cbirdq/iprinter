package com.printer.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.printer.dao.impl.OrderDao;
import com.printer.dao.impl.PriceDao;
import com.printer.model.Entry;
import com.printer.model.Merchant;
import com.printer.model.Order;
import com.printer.model.Page;
import com.printer.model.Price;
import com.printer.model.User;
import com.printer.service.impl.OrderService;
import com.printer.service.impl.PriceService;
import com.printer.util.UuidGenerator;

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
		
		//从session中获取entry
		if(action != null && action.equals("get-session-entries")) {
			JSONObject json = new JSONObject();
			Collection<Entry> entries = EntryManager.getEntries(request).values();
			json.put("entries", entries);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(json.toString());
		}
		
		//查询订单详情
		if(action != null && action.equals("get-order-detail")) {
			String orderid = request.getParameter("orderid");
			OrderService orderservice=new OrderService(new OrderDao());	
			Order order = orderservice.get(orderid);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/orderdetail.jsp").forward(request, response);
		}
	
		//未确认的订单
		if(action != null && action.equals("merchant_unacked")) {
			if(!UserManager.isLogin(request))
				return;
			Merchant merchant=(Merchant) UserManager.getUser(request);
			OrderService orderservice=new OrderService(new OrderDao());	
			Page page = null;
			int pageNo = request.getParameter("pageNo") == null ? 
					1 : Integer.parseInt(request.getParameter("pageNo"));
			
			if(request.getParameter("pageSize") != null) {
				int pageSize = Integer.parseInt(request.getParameter("pageSize"));
				page = new Page(pageNo, pageSize);
			} else {
				page=new Page(pageNo);
			}
			Map<String,Object> args=new HashMap<String,Object>();
			args.put("shopid",merchant.getShop().getId());
			args.put("status","0");			
			Page pageorder=(Page)orderservice.getPagedOrder(args, page);	       
			request.setAttribute("pageorder", pageorder);
			request.getRequestDispatcher("/merchant_unacked.jsp").forward(request, response);
		}
		//待打印的订单
		/*if(action!=null && action.equals("merchant_unprint")) {
			Map<String,Object> args=new HashMap<String,Object>();
			args.put("shopid", merchant.getShop().getId());
			args.put("status","1");
			List<Order> listorder=(List<Order>) orderservice.getPagedOrder(args, page);
			request.setAttribute("listorder", listorder);
			response.sendRedirect("merchant_unprint.jsp");
		}
		//待签收的订单
		if(action!=null && action.equals("merchant_unsign")){
			Map<String,Object> args=new HashMap<String,Object>();
			args.put("shopid", merchant.getShop().getId());
			args.put("status","2");
			List<Order> listorder=(List<Order>) orderservice.getPagedOrder(args, page);
			request.setAttribute("listorder", listorder);
			response.sendRedirect("merchant_unsign.jsp");			
		}
		//全部订单
		if(action!=null && action.equals("merchant_allorder")){
			List<Order> listorder=(List<Order>) orderservice.getPagedOrderByShopid(merchant.getShop().getId(), page);
			request.setAttribute("listorder", listorder);
			response.sendRedirect("merchant_allorder.jsp");
		}
		*/
		/*else if(action!=null && action.equals("merchant_acked")){
			String orderid=request.getParameter("orderid").toString();
			
		}*/
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		//单个条目的打印设置
		if(action != null && action.equals("set-entry")) {
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
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			response.getWriter().print(json.toString());
			
		} 
		if(action != null && action.equals("post-count")) {
			//获取参数
			String entryid = request.getParameter("entryid");
			int printcount = Integer.parseInt(request.getParameter("printcount"));
			//从session中获取对应的Entry对象
			Entry theEntry = EntryManager.getEntry(request, entryid);
			theEntry.setPrintcount(printcount); //设置打印数量
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			response.getWriter().print(json.toString());
		}
		
		//保存订单
		if(action != null && action.equals("post-order")) {
			String shopid = request.getParameter("shopid");
			String fetchtime = request.getParameter("fetchtime");
			int filenumber = Integer.parseInt(request.getParameter("filenumber"));
			int payway = Integer.parseInt(request.getParameter("payway"));
			float money = Float.parseFloat(request.getParameter("money"));
			String comment = request.getParameter("comment");
			
			Order order = new Order();
			String orderid = UuidGenerator.getUUID();
			order.setId(orderid);
			order.setCreatetime(new Timestamp(new Date().getTime()));
			order.setShopid(shopid);
			order.setFetchtime(Timestamp.valueOf(fetchtime));
			order.setFilenumber(filenumber);
			order.setPayway(payway);
			order.setMoney(money);
			order.setPayment(0);
			order.setPointpay(0);
			order.setComment(comment);
			
			order.setEntries(EntryManager.getEntries(request).values());
			order.setStatus("0");
			order.setUser((User)UserManager.getUser(request));
			
			//保存订单
			OrderService service = new OrderService(new OrderDao());
			service.save(order);
			
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("orderid", orderid);
			response.getWriter().print(json.toString());
		}
		
		
		
	}

}
