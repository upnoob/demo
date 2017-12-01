package com.cust.demo;

import java.sql.Blob;
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
	private String sex;
	private Date birthday;
	private Blob picture;
	private Address address;

	public Students() {
	}

	

	public Students(int sid, String sname, String sex, Date birthday, Blob picture) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.sex = sex;
		this.birthday = birthday;
		this.picture = picture;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	public Blob getPicture() {
		return picture;
	}



	public void setPicture(Blob picture) {
		this.picture = picture;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "Students [sid=" + sid + ", sname=" + sname + ", sex=" + sex + ", birthday=" + birthday + ", picture="
				+ picture + ", address=" + address + "]";
	}



	

    	
}
