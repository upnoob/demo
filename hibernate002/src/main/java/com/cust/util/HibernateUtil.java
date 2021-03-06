package com.cust.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Session session;
	
	static {
		//创建Configuration对象，读取hibernate.cfg.xml文件
		Configuration config = new Configuration().configure();
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		StandardServiceRegistry ssr = ssrb.build();
		sessionFactory = config.buildSessionFactory(ssr);
	}
	
	/**
	 * 获取SessionFactory
	 */
	public static SessionFactory getSessionFacory() {
		return sessionFactory;
	}
	
	
	/**
	 * 获取Session
	 * @return
	 */
	public static Session getSession() {
		session = sessionFactory.openSession();
		return session;
	}
	
	
	public static void closeSession() {
		if(session != null) {
			session.close();
		}
	}
	
	public static void closeSessionFactory(){
		if(sessionFactory != null) {
			sessionFactory.close();
		}
	}
	
}
