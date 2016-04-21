package com.printer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;


public class Downloader {
	
	public static void download(HttpServletResponse response, String url) {
		
		File file = new File(url);
		if(!file.exists()) {
			return;
		}
		
		String fileName = url.substring(url.lastIndexOf("\\")+1);
		FileInputStream in = null;
		OutputStream out = null;
		try {
			//设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			//读取要下载的文件，保存到文件输入流中
			in = new FileInputStream(url);
			//创建输出流
			response.getOutputStream();
			
			//创建缓存区
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = in.read(buffer))> 0) {
				//输入缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			
			in.close();
			out.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
