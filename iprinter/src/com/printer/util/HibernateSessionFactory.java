package com.printer.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author ThinkPad
 *
 */
public class HibernateSessionFactory {
	
	private static Configuration cf = null;
	private static SessionFactory sf = null;
	
	static {
		cf = new AnnotationConfiguration().configure();
		sf = cf.buildSessionFactory(); //创建session
		System.out.println("SessionFactory创建成功");
	}
	
	public static Session getSession() {
		Session session = null;
		if(session == null || session.isOpen() == false) {
			session = null;
			session = sf.getCurrentSession();
		}
		return session;
	}
	
	
}
