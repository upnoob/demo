package com.cust.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

/**
 * openSession()  jdbc的session  session不会自动关闭，每次都会创建一个新对象,引用完需要手动关闭
 * getCurrentSession() jta   不需要手动关闭，单例模式,事务结束后自动关闭连接对象
 * @author upnoob
 *
 */
public class Test2 {

	@Test
	public void testOpenSession(){
		//创建配置对象
		Configuration configuration = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//会话对象
		Session session = sessionFactory.openSession();
	
		if(session != null) {
			System.out.println("open session 创建成功！");
		}else {
			System.out.println("opne session 创建失败！");
		}
	}
	
	
	@Test
	public void testGetCurrentSession(){
		//创建配置对象
		Configuration configuration = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//会话对象
		Session session = sessionFactory.getCurrentSession();
	
		if(session != null) {
			System.out.println("get current session 创建成功！");
		}else {
			System.out.println("get current session 创建失败！");
		}
	}
}
