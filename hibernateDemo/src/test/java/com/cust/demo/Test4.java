package com.cust.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试 save update delete 
 * get/load  单个记录
 * 
 * get/load区别
 * 		1.get 方法不考虑缓存，会在调用后立刻向数据库发送sql语句，返回持久化对象
 * 		2.load方法，会在方法调用后返回代理对象，改代理对象只保存了实体对象的id， 直到使用对象的非主键属性时才会发出SQL语句
 * @author upnoob
 *
 */
public class Test4 {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void before(){
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//会话对象
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}

	@After
	public void after(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testSave(){
		Students stu = new Students();
		stu.setSname("tom");
		stu.setSex("男");
		stu.setBirthday(new Date());
		stu.setAddress(new Address("223600", "130383873", "中国 长春"));
		
		session.save(stu);
	}
	
	@Test
	public void testGet(){  //get 方法不考虑缓存，会在调用后立刻向数据库发送sql语句，返回持久化对象
		Students stu = (Students)session.get(Students.class, 1);   
		System.out.println(stu.getClass().getName());
//		System.out.println(stu);
	}
	
	@Test
	public void testLoad(){ //load方法，会在方法调用后返回代理对象，改代理对象只保存了实体对象的id， 直到使用对象的非主键属性时才会发出SQL语句
		Students stu = (Students)session.load(Students.class, 1);   
		System.out.println(stu.getClass().getName());
//		System.out.println(stu);
	}
	@Test
	public void testUpdate(){
		Students stu = (Students)session.get(Students.class, 1);   
		stu.setSname("lisa");
		session.update(stu);
		System.out.println(stu.getClass().getName());
//		System.out.println(stu);
	}
	
	
	@Test
	public void testDelete(){
		Students stu = (Students)session.get(Students.class, 1);   
		System.out.println(stu.getClass().getName());
		session.delete(stu);
//		System.out.println(stu);
	}
}
