package com.printer.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.printer.model.Page;
import com.printer.util.HibernateSessionFactory;

public class BaseDao<M extends Serializable, PK extends Serializable> {
	
	private Class<M> entityClass;
	
	public BaseDao() {
		Type genericType = getClass().getGenericSuperclass();//获得带有泛型的父类
		Type[] params = ((ParameterizedType)genericType).getActualTypeArguments();//ParameterizedType，参数化类型，即泛型
		entityClass =  (Class) params[0];
		System.out.println(entityClass.getName());
	}
	
	//增
	public void save(M model) throws Exception {
		Transaction tx = null;
		Session session = HibernateSessionFactory.getSession();
		//开始事务
		try{
			tx = session.beginTransaction();
			session.save(model);
			//提交事务
			tx.commit(); 
		} catch(Exception e) {
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	public void delete(PK id) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.delete(id);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	//删
	public void remove(M model) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.delete(model);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	//改
	public void update(M model) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.update(model);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	
	//查
	public M get(PK id) {
		M model = null;
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			model = (M) session.get(entityClass, id);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		}
		return model;
	}


	public M load(PK id) {
		M model = null;
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			model = (M) session.load(entityClass, id);
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return model;
	}

	public List<M> loadAll() {
		return null;
	}
   public List<M> ListAll(){
	   return find("from " + entityClass.getName());
   }
   
   public M findOne(String hql) {
	    M model = null;
	    Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			model = (M) session.createQuery(hql).uniqueResult();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return model;
   }
   
   
   
	public List<M> find(String hql) {
		List<M> lists = null;
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			lists = (List<M>) session.createQuery(hql).list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return lists;
	}

	public List<M> find(String hql, Object[] args) {
		List<M> lists = null;
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = createQuery(hql, args);
			lists = query.list();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return lists;
	}
	
	
	public M findOne(String hql, Object[] args) {
	    M model = null;
	    Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = createQuery(hql, args);
			model = (M) query.uniqueResult();
			session.getTransaction().commit();
		} catch(Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		return model;
   }

	public Page pagedQuery(String hql, int pageNo, int pageSize,
			Object... params) {
		//count查询
		String countQueryString = "select count(*)" + removeSelect(removeOrders(hql));
		List countlist = find(countQueryString, params);
		long totalCount = (long) countlist.get(0);
		
		if(totalCount < 1)
			return new Page();
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, params);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		
		return new Page(startIndex, totalCount, pageSize, list);
	}

	//创建Query对象
	public Query createQuery(String hql, Object[] params) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		for(int i = 0; i < params.length; i++) 
			query.setParameter(i, params[i]);
		return query;
	}
		
		
	//去除hql的orderby子句
	private String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while(m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	//去除hql中的select子句
	private String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		return hql.substring(beginPos);
	}


	
}
