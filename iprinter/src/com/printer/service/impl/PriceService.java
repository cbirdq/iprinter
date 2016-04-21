package com.printer.service.impl;

import java.util.List;
import java.util.Map;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.PriceDao;
import com.printer.model.Price;
import com.printer.service.BaseService;


public class PriceService extends BaseService<Price, Integer> {

	public PriceService(BaseDao baseDao) {
		super(baseDao);
	}
	
	
	/**
	 * 获得所有纸张大小
	 * @return
	 */
	public List<String> getAllPaperSize() {
		return ((PriceDao)getBaseDao()).getAllPaperSize();
	}
	
	/**
	 * 查询价格
	 * @param map
	 * @return
	 */
	public List<Float> getPriceBy(Map<String, Object> map) {
		return ((PriceDao)getBaseDao()).getPriceBy(map);
	}
	
	/**
	 * 根据纸张大小，双面打印和彩印查询Price对象
	 * @param pagesize
	 * @param biside
	 * @param color
	 * @return
	 */
	public Price getBy(String papersize, int biside, int color) {
		return ((PriceDao)getBaseDao()).getBy(papersize, biside, color);
	}
	
	
}
