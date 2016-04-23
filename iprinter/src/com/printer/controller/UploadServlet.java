package com.printer.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.printer.dao.impl.PriceDao;
import com.printer.model.Entry;
import com.printer.model.Files;
import com.printer.model.Price;
import com.printer.service.impl.PriceService;
import com.printer.util.Uploader;
import com.printer.util.UuidGenerator;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		if(action != null && action.equals("delete-file")) {
			JSONObject jsonObject = new JSONObject();
			String entryid = request.getParameter("entryid");
			//从session中获取对应的Entry对象
			Entry theEntry = EntryManager.removeEntry(request, entryid);
			
			String url = theEntry.getFile().getUrl();
			File file = new File(url);
			if(file.exists()) {
				file.delete();
				jsonObject.put("status", "ok");
			} else {
				jsonObject.put("status", "no");
			}
			response.getWriter().print(jsonObject.toString());
		}
		
	}
	


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//文件上传处理
		Uploader uploader = new Uploader(request);
		
		JSONObject jsonObject = new JSONObject();
		
		String originalname = uploader.getOriginalName();
		//如果没有上传过，则上传该文件
		if(EntryManager.findEntryByOriginalName(request, originalname) == null) {
			//上传文件
			try {
				uploader.upload();
				
				String filename = uploader.getFileName();
				//每上传一份文件，就产生一个entry，并默认打印一份一页的文档
				Entry entry = new Entry(UuidGenerator.getUUID(), 1, 1);
				
				Files file = new Files(UuidGenerator.getUUID(), filename, originalname);
				
				file.setUrl(uploader.getUrl());
				entry.setFile(file);
				
				//默认打印A4 非双面 非彩印
				PriceService priceService = new PriceService(new PriceDao());
				Price price = priceService.getBy("A4", 0, 0);
				entry.setPrice(price);
				entry.setMoney(entry.getPrintcount() * price.getPrice());
				//将entry缓存在session中，以备下次快速获取
				EntryManager.saveEntry(request, entry);
				
			} catch (Exception e) {
				//上传失败的页面提示信息
				jsonObject.put("error", "文件" + originalname + "上传失败.");
				e.printStackTrace();
			}
		} else {
			jsonObject.put("error", "文件" + originalname + "已经存在，请勿重复上传！");
		}
		response.setCharacterEncoding("utf-8");
		//下面这行代码不能省略，fileinput插件要求必须返回一个json字符串
		response.getWriter().print(jsonObject.toString()); 
	}
	
}
