package com.printer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.printer.model.Files;
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Uploader uploader = new Uploader(request);
		JSONObject jsonObject = new JSONObject();
		try {
			//上传文件
			uploader.upload();
			
System.out.println(uploader.getFileName() + " " + uploader.getOriginalName());
			
			
			//将文件名等信息暂存在session中，以备下次直接从内存读取
			HttpSession session = request.getSession();
			List<Files> files = (List<Files>) session.getAttribute("files");
			if(files == null) {
				files = new ArrayList<Files>();
				session.setAttribute("files", files);
			}
			files.add(new Files(UuidGenerator.getUUID(), 
					uploader.getFileName(), uploader.getOriginalName()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.getWriter().print(jsonObject.toString());
	}

}
