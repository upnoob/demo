package com.cust.demo;

import java.io.Serializable;

public class Student implements Serializable {
	private int sid;
	private String sname;
	private String sex;
	private int gid;

	private Grade grade;


	public Student(String sname, String sex) {
		this.sname = sname;
		this.sex = sex;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Student() {
	}

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}

	@Override
	public String toString() {
		return "Student{" +
				"sid=" + sid +
				", sname='" + sname + '\'' +
				", sex='" + sex + '\'' +
				", gid=" + gid +
				", grade=" + grade +
				'}';
	}
}
