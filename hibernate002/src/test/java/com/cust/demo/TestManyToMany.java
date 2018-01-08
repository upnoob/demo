package com.cust.demo;

import com.cust.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * 测试多对多
 * Created by codingbug on 2018/1/8.
 */
public class TestManyToMany {
    public static void main(String[] args) {
        testManyToMany();
    }

    public static void testManyToMany(){

        Project p1 = new Project(1, "project one");
        Project p2 = new Project(2, "project two");
        Employee e1 = new Employee(1, "tom");
        Employee e2 = new Employee(2, "lisa");

        p1.getEmployees().add(e1);
        p1.getEmployees().add(e2);

        p2.getEmployees().add(e1);

        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(p1);
        session.save(p2);
        transaction.commit();
        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();
    }
}
