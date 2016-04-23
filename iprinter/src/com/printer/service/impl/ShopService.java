package com.printer.service.impl;

import java.util.List;

import com.printer.dao.BaseDao;
import com.printer.dao.impl.ShopDao;
import com.printer.model.Shop;
import com.printer.service.BaseService;

public class ShopService extends BaseService{

	public ShopService(BaseDao baseDao) {
		super(baseDao);
	}

	/**
	 * 根据学校编号查找打印店
	 * @param schoolid
	 * @return
	 */
	public List<Shop> getShopBySchoolid(int schoolid) {
		return ((ShopDao)getBaseDao()).getShopBySchoolid(schoolid);
	}

}
