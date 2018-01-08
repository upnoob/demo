package com.cust.demo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by codingbug on 2018/1/6.
 */
public class Employee {
    private int empid;
    private String empname;
    private Set<Project> projects = new HashSet<Project>();

    public int getEmpid() {
        return empid;
    }

    public Employee() {
    }

    public Employee(int empid, String empname) {
        this.empid = empid;
        this.empname = empname;
    }

    public Employee(int empid, String empname, Set<Project> projects) {
        this.empid = empid;
        this.empname = empname;
        this.projects = projects;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }


}
