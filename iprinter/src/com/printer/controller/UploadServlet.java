package com.printer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.printer.util.Uploader;

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
		
		Uploader uploader = new Uploader(request);
		try {
			uploader.upload();
			String fileName = uploader.getFileName();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("initialPreview", new String[] {"<img style='height:160px' src='uploadFiles/"+ fileName + "' class='file-preview-image' >"});
			
			JSONObject tmpjo = new JSONObject();
			tmpjo.put("caption", "Architecture-" + fileName +".jpg");
			tmpjo.put("width", "120px");
			tmpjo.put("url", "uploadFiles/");
			tmpjo.put("key", fileName);
			jsonObject.put("initialPreviewCofig", tmpjo);
			
			jsonObject.put("append", "ture");
			response.getWriter().print(jsonObject.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
