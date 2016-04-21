package com.printer.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用Apache文件上传组件处理文件上传
 * 
 * 注意细节：
 * 1、为保证服务器安全，上传文件应该放在外界无法直接访问的目录下，比如WEB-INF目录下
 * 2、为防止文件覆盖，必须为每个上传文件产生一个唯一的文件名
 * 3、为防止一个目录下出现太多文件，要使用hash算法打散存储
 * 4、要限制文件上传的最大值
 * 5、要限制文件上传的类型，接收到上传文件名时，要进行后缀名的判定
 * 
 */
public class Uploader {
	
	//文件保存的目录
	public static String SAVE_PATH = "WEB-INF\\upload";
	
	//上传时生成的临时文件存放目录
	private static String TEMP_PATH = "WEB-INF\\temp";
	
	// 文件大小常量, 单位kb
	private static int MAX_SIZE = 500 * 1024;
	
	// 文件允许格式
	private static String[] ALLOW_TYPE = { ".rar", ".doc", ".docx", ".zip", ".pdf",
			".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
		
	
	private HttpServletRequest request = null;
	
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";
	
	// 文件大小限制，单位Byte
	private long maxSize = 0;
	
	private String error = "";

	private Map<String, String> params = null;
	
	// 上传的文件数据
	private InputStream inputStream = null;
	
	
	
	static {
		Properties properties = new Properties();
		try {
			properties.load(Uploader.class.getResourceAsStream("/application.properties"));
			String rootPath = properties.getProperty("setupPath");
			
			String savePath = properties.getProperty("savePath");
			if(savePath != null)
				SAVE_PATH = rootPath + savePath;
			else
				SAVE_PATH = rootPath + SAVE_PATH;
			
			TEMP_PATH = rootPath + TEMP_PATH;
			
			String maxSize = properties.getProperty("maxSize");
			if(maxSize != null)
				MAX_SIZE = Integer.parseInt(maxSize);
			
			String allowType = properties.getProperty("allowType");
			if(allowType != null)
				ALLOW_TYPE = allowType.split(",");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	


	public Uploader(HttpServletRequest request) {
		this.request = request;
		this.params = new HashMap<String, String>();
		this.setMaxSize(MAX_SIZE);
		//文件上传参数解析
		this.parseParams();
	}

	/**
	 * 文件上传
	 * @throws Exception
	 */
	public void upload() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
		if (!isMultipart) {
			this.error = "No MultipartContent";
			return;
		}

		if (this.inputStream == null) {
			this.error = "No InputStream";
			return;
		}

		try {

			if (!this.checkFileType(this.originalName)) {
				this.error = "Invalid Type";
				return;
			}
			String savePath = this.getFolder(SAVE_PATH);

			this.fileName = this.getName(this.originalName);
			this.type = this.getFileExt(this.fileName);
			this.url = savePath + "\\" + this.fileName;
			
			
			FileOutputStream fos = new FileOutputStream(savePath + "\\" + this.fileName);
			BufferedInputStream bis = new BufferedInputStream(this.inputStream);
			byte[] buff = new byte[1024];
			int count = -1;
			while ((count = bis.read(buff)) != -1) {
				fos.write(buff, 0, count);
			}

			bis.close();
			fos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			this.error = "Upload Failed";
		}

	}


	public String getParameter(String name) {

		return this.params.get(name);

	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(ALLOW_TYPE).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private void parseParams() {
		//1. 创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//2. 设置工厂的缓冲区大小，如果上传文件大小超过缓冲区时，就生成一个临时文件存放到指定的临时目录下，
		factory.setSizeThreshold(1024*100); //将缓冲区大小设置为100k，不设置默认为10k
		//3. 设置上传时临时文件的保存目录
		File tmpFile = new File(TEMP_PATH);
		if(!tmpFile.exists()) {
			//创建临时目录
			tmpFile.mkdir();
		}
		factory.setRepository(tmpFile);
		try {
			//4.创建一个文件上传解析器
			ServletFileUpload uploader = new ServletFileUpload(factory);
			//监听文件上传进度
			uploader.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					System.out.println("文件大小为：" + pContentLength + "，当前已处理：" + pBytesRead);
				}
			});
			//解决中文文件名的乱码问题
			uploader.setHeaderEncoding("UTF-8");
			uploader.setSizeMax(this.maxSize);

			//5.使用ServletFileUpload解析器解析表单数据，返回一个迭代器（每次移动都会指向一个表单的输入项）
			FileItemIterator fileItemItor = uploader.getItemIterator(this.request);

			while (fileItemItor.hasNext()) {
				FileItemStream item = fileItemItor.next();
				// 普通参数存储
				if (item.isFormField()) {
					this.params.put(item.getFieldName(),
							this.getParameterValue(item.openStream()));
				} 
				//表单上传的是文件数据
				else {
					// 只保留一个
					if (this.inputStream == null) {
						this.inputStream = item.openStream();
						this.originalName = item.getName();
						return;
					}
				}
			}

		} catch (Exception e) {
			this.error = "Unknow Error";
			e.printStackTrace();
		}

	}

	
	/**
	 * 依据原始文件名生成新文件名
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis() + this.getFileExt(fileName);
	}

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * 
	 * @param path
	 * @return
	 */
	private String getFolder(String savePath) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		savePath += "\\" + formater.format(new Date());
		File dir = new File(savePath);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.error = "No Folder Found";
				return "";
			}
		}
		return savePath;
	}
	


	/**
	 * 从输入流中获取字符串数据
	 * 
	 * @param in
	 *            给定的输入流， 结果字符串将从该流中读取
	 * @return 从流中读取到的字符串
	 */
	private String getParameterValue(InputStream in) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String result = "";
		String tmpString = null;

		try {

			while ((tmpString = reader.readLine()) != null) {
				result += tmpString;
			}

		} catch (Exception e) {
			// do nothing
		}

		return result;

	}

	private byte[] getFileOutputStream(InputStream in) {

		try {
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			return null;
		}

	}


	public void setMaxSize(long size) {
		this.maxSize = size * 1024;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}


	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}

	public String getError() {
		return error;
	}

	
}