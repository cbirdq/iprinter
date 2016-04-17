package com.printer.service.impl;

import java.util.List;

import org.json.JSONObject;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.SchoolDao;
import com.printer.model.School;
import com.printer.service.BaseService;

public class LocationService extends BaseService<School, Integer> {
	
	public LocationService(BaseDao baseDao) {
		super(baseDao);
	}
	
	
	
	public static void main(String[] args) {
		BaseService service = new LocationService(new SchoolDao());
		List<School> schools = service.listAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("school-items", schools);
		System.out.println(jsonObject.toString());
		
	}

}
