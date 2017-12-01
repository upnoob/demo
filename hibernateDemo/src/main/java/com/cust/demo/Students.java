package com.cust.demo;

import java.util.Date;

/**
 * java beans设计原则 1.public class 2.提供一个公有的不带参数的方法 3.属性私有 4.set/get方法
 * 
 * @author upnoob
 *
 */
public class Students {

	private int sid;
	private String sname;
	private String gender;
	private Date birthday;

	public Students() {
	}

	public Students(int sid, String sname, String gender, Date birthday) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.birthday = birthday;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Studects [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", birthday=" + birthday + "]";
	}

}
