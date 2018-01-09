package com.cust.demo;

import com.cust.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
//		add();
//		queryStudentsByGrade();
//		update();
//		delete();
//		add2(); //测试多对一
		testHQL();
	}


	public static void testHQL(){

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		String hql = " from Student";
		Query query = session.createQuery(hql);
		List<Student> lists = query.list();
		for (Student stu: lists){
			System.out.println(stu.getSname());
		}
		HibernateUtil.closeSession();
		HibernateUtil.closeSessionFactory();

	}


	/**
	 * 测试多对一的关系
	 */
	public static void add2(){
		Grade grade = new Grade("java 1 班", "java 开发学习班 cust 哈哈哈哈");
		Student stu1 = new Student("tom", "男");
		Student stu2 = new Student("lisa", "女");

		grade.getStudents().add(stu1);
		grade.getStudents().add(stu2);
		stu1.setGrade(grade);
		stu2.setGrade(grade);

		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
//		session.save(stu1);
//		session.save(stu2);
		transaction.commit();
		HibernateUtil.closeSession();
		HibernateUtil.closeSessionFactory();
	}

	/**
	 * 测试一对多
	 */
	public static void add(){
		Grade grade = new Grade("java 1 班", "java 开发学习班");
		Student stu1 = new Student("tom", "男");
		Student stu2 = new Student("lisa", "女");
		
		grade.getStudents().add(stu1);
		grade.getStudents().add(stu2);
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(grade);
		session.save(stu1);
		session.save(stu2);
		transaction.commit();
		HibernateUtil.closeSession();
		HibernateUtil.closeSessionFactory();
	}
	
	/**
	 * 查询班级和学生信息
	 */
	public static void queryStudentsByGrade() {
		Session session = HibernateUtil.getSession();
		Grade grade = (Grade) session.get(Grade.class, 1);
		System.out.println(grade.getGid() + "\t" + grade.getGname() + "\t" + grade.getGdesc());
		
		Set<Student> stus = grade.getStudents();
		for(Student s: stus){
			System.out.println(s.getSname() + "\t" + s.getGid() + "\t" + s.getSex());
		}
		HibernateUtil.closeSession();
		HibernateUtil.closeSessionFactory();
	}
	
	
	/**
	 * 修改学生信息
	 */
	public static void update() {
		Grade g = new Grade();
		g.setGname("java 二班");
		g.setGdesc("java 基础班级");
		
		Session session = HibernateUtil.getSession();
		Transaction ta = session.beginTransaction();
		Student stu = (Student) session.get(Student.class, 1);
		g.getStudents().add(stu);
		session.save(g);
		ta.commit();
		HibernateUtil.closeSession();
		HibernateUtil.closeSessionFactory();
		
	}
	
	public static void delete(){
		Session session = HibernateUtil.getSession();
		Transaction ta = session.beginTransaction();
		Student stu = (Student) session.load(Student.class, 2);
		session.delete(stu);
		ta.commit();
		HibernateUtil.closeSession();
		HibernateUtil.closeSessionFactory();
	}
}
