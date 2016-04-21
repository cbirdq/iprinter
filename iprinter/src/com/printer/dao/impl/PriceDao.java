package com.printer.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.printer.dao.BaseDao;
import com.printer.model.Price;
import com.printer.util.HibernateSessionFactory;


public class PriceDao extends BaseDao<Price, Integer> {
	
	private static String SELECT_DISTINCT_PAPERSIZE = "select distinct papersize from price";
	
	private static String GET_PRICE_BY_MULTI = "select price from price where 1=1";
	
	private static String GET_BY_PAPERSIZE_BISIDE_COLOR = "from Price where papersize=? and biside=? and color=?";
	
	/**
	 * 获得所有纸张大小
	 * @return
	 */
	public List<String> getAllPaperSize() {
		Session session = HibernateSessionFactory.getSession();
		List<String> list = session.createSQLQuery(SELECT_DISTINCT_PAPERSIZE).list();
		session.close();
		return list;
	}
	
	/**
	 * 查询价格
	 * @param map
	 * @return
	 */
	public List<Float> getPriceBy(Map<String, Object> map) {
		StringBuffer sqlsb = new StringBuffer(GET_PRICE_BY_MULTI);
		for(String key: map.keySet())
			sqlsb.append(" and " + key + "=\"" + map.get(key) + "\"");

		Session session = HibernateSessionFactory.getSession();
		List<Float> list = session.createSQLQuery(sqlsb.toString()).list();
		session.close();
		return list;
	}
	
	/**
	 * 
	 * @param pagesize
	 * @param biside
	 * @param color
	 * @return
	 */
	public Price getBy(String papersize, int biside, int color) {
		return super.findOne(GET_BY_PAPERSIZE_BISIDE_COLOR, new Object[] {papersize, biside, color});
	}
	
	
	public static void main(String[] args) {
		PriceDao dao = new PriceDao();
		List<String> list = dao.getAllPaperSize();
		for(String s : list)
			System.out.println(s);
	}
	
}
