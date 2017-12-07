package com.cust.demo;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cust.util.HibernateUtil;

public class Test {
	
	public static void main(String[] args) {
//		add();
		queryStudentsByGrade();
	}

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
	}
	
	public static void queryStudentsByGrade() {
		Session session = HibernateUtil.getSession();
		Grade grade = (Grade) session.get(Grade.class, 1);
		System.out.println(grade.getGid() + "\t" + grade.getGname() + "\t" + grade.getGdesc());
		
		Set<Student> stus = grade.getStudents();
		for(Student s: stus){
			System.out.println(s.getSname() + "\t" + s.getGid() + "\t" + s.getSex());
		}
		
	}
}
