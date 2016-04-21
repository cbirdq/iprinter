package com.printer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.printer.model.Entry;
import com.printer.model.Files;

/**
 * 
 * @author ThinkPad
 *
 */
public class EntryManager {
	
	/**
	 * 将Entry对象保存到Session中
	 * @param request
	 * @param entry
	 */
	public static void saveEntry(HttpServletRequest request, Entry entry) {
		getEntries(request).put(entry.getId(), entry);
	}
	
	/**
	 * 从session中删除entryid对应的entry
	 * @param request
	 * @param entryid
	 */
	public static Entry removeEntry(HttpServletRequest request, String entryid) {
		return getEntries(request).remove(entryid);
	}
	
	
	public static void removeEntry(HttpServletRequest request, Entry entry) {
		getEntries(request).remove(entry.getId());
	}
	
	/**
	 * 从session中删除entryid对应的entry
	 * @param request
	 * @param entryid
	 */
	public static Entry getEntry(HttpServletRequest request, String entryid) {
		return getEntries(request).get(entryid);
	}
	
	/**
	 * 根据文件名删除entry
	 * @param request
	 * @param fileName
	 * @return
	 */
	/*public static Entry removeEntryByFileName(HttpServletRequest request, String fileName) {
		Entry theEntry = null;
		Files theFile = null;
		Map<String, Entry> entries = getEntries(request);
		for(String key : entries.keySet()) {
			theFile = entries.get(key).getFile();
			if(theFile != null && theFile.getFilename().equals(fileName)) {
				theEntry = entries.remove(key);
			}
		}
		return theEntry;
	}*/
	
	/**
	 * 根据文件上传之前的文件名查找entry
	 * @param request
	 * @param originalName
	 * @return
	 */
	public static Entry findEntryByOriginalName(HttpServletRequest request, String originalName) {
		Entry theEntry = null;
		Files theFile = null;
		Map<String, Entry> entries = getEntries(request);
		for(String key : entries.keySet()) {
			theFile = entries.get(key).getFile();
			if(theFile != null && theFile.getOriginalname().equals(originalName)) {
				theEntry = entries.get(key);
			}
		}
		return theEntry;
	}
	
	public static Map<String, Entry> getEntries(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Entry> entries = (Map<String, Entry>) session.getAttribute("entries"); //key对应的是entryid
		if(entries == null) {
			entries = new HashMap<String, Entry>();
			session.setAttribute("entries", entries);
		}
		return entries;
	}
	
}
