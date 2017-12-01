package com.cust.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Test3 {

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
	
	/*@Test
	public void test(){
		
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				connection.setAutoCommit(true);
				
				Students stu = new Students();
				stu.setSname("tom");
				stu.setSex("男");
				stu.setBirthday(new Date());
				
				
				session.save(stu);
				session.flush();
			}
		});
		
	
	}*/
	
	
	@Test
	public void testSave() throws Exception {
		Students stu = new Students();
		File file = new File("f:/lx.jpg");
		
		InputStream inputStream = new FileInputStream(file);
		
		Blob image = Hibernate.getLobCreator(session).createBlob(inputStream, inputStream.available());
		
		stu.setSname("tom");
		stu.setSex("男");
		stu.setBirthday(new Date());
		stu.setPicture(image);
		
		session.save(stu);
		
	}
}
