package com.cust.demo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by codingbug on 2018/1/6.
 */
public class Project {
    private int proid;

    private String proname;

    //添加一个员工的集合
    private Set<Employee> employees = new HashSet<Employee>();

    public Project() {
    }

    public Project(int proid, String proname) {
        this.proid = proid;
        this.proname = proname;
    }

    public Project(int proid, String proname, Set<Employee> employees) {
        this.proid = proid;
        this.proname = proname;
        this.employees = employees;
    }

    public int getProid() {
        return proid;
    }

    public void setProid(int proid) {
        this.proid = proid;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


}
