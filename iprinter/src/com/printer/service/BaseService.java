package com.printer.service;

import java.io.Serializable;
import java.util.List;

import com.printer.dao.BaseDao;

public class BaseService<M extends Serializable, PK extends Serializable> {

	private BaseDao baseDao;
	
	public BaseService(BaseDao baseDao)
	{
		this.baseDao=baseDao;
	}
	public void save(M model) {
		baseDao.save(model);
	}
	
	public void update(M model) {
		baseDao.update(model);
		
	}


	public void delete(PK id) {
		baseDao.delete(id);
		
	}

	public M get(PK id) {
		M m=(M) baseDao.get(id);
		return m;
	}


	public List<M> listAll() {
		
		return baseDao.ListAll();
	}
	public List<M> find(String sql)
	{
		
		return baseDao.find(sql);
	}
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	

}
