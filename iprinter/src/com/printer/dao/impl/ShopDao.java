package com.printer.dao.impl;

import java.util.List;

import com.printer.dao.BaseDao;
import com.printer.model.Shop;


public class ShopDao extends BaseDao<Shop, String> {

	private static String GET_BY_SHCOOLID = "From Shop where schoolid=?";
	
	
	/**
	 * 根据学校编号查找打印店
	 * @param schoolid
	 * @return
	 */
	public List<Shop> getShopBySchoolid(int schoolid) {
		return super.find(GET_BY_SHCOOLID, new Object[] {schoolid});
	}
	
	
}
