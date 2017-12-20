package com.cust.demo.exception;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象序列化两种方式
 * 	     1.实现Serializable接口,序列化将自动进行。
 * 	     2.实现Externalizable接口,该方式不能自动序列化，需要在writeExternal方法中手动指定需要序列化的变量。
 * @author upnoob
 *
 */
public class EOFExceptionDemo {
	
	public static void main(String[] args) throws Exception{
		
		User u1 = new User("tom", "男", 20);
		User u2 = new User("lisa", "女", 21);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		
		oos.writeObject(u1);
		oos.writeObject(u2);
		oos.writeObject(null);
		
		byte[] data = bos.toByteArray();
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(bis);
		
		System.out.println(ois.readObject());
		System.out.println(ois.readObject());
		System.out.println(ois.readObject());
		System.out.println(ois.readObject());
		
	}
	

}

class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public String name;
	/**
	 * 1、一旦变量被transient 修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
	 * 2、transient关键字只能修饰实例变量（类变量），不能修饰成员变量（本地变量、局部变量），也不能修饰方法和类
	 * 3、如果transient修饰的是用户自定义的类变量，则该类需要实现Serializable接口。
	 * 4、被transient 修饰的变量不能被序列化，一个static修饰的变量不管是否被transient修饰，均不能被序列化。
	 * 
	 */
	public transient String sex;  //transient修饰的不能被序列化
	public int age;
	
	public User(String name, String sex, int age){
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
	
}
