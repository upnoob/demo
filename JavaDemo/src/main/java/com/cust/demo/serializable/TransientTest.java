package com.cust.demo.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author upnoob
 * @description 使用transient关键字不序列化某个变量，注意读取的时候，读取数据的顺序一定要和存放数据的顺序保持一致。
 * @conclusion 
 * 		1、一旦变量被transient 修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 * 		2、transient关键字只能修饰实例变量（类变量），不能修饰成员变量（本地变量、局部变量），也不能修饰方法和类
 * 		3、如果transient修饰的是用户自定义的类变量，则该类需要实现Serializable接口。
 * 		4、被transient 修饰的变量不能被序列化，一个static修饰的变量不管是否被transient修饰，均不能被序列化。
 * @version 1.0
 * @date 2017-12-20
 */
public class TransientTest {
	public static void main(String[] args) {
		User user = new User();
        user.setUsername("Alexia");
        user.setPasswd("123456");
        
        System.out.println("read before Serializable: ");
        System.out.println("username: " + user.getUsername());
        System.err.println("password: " + user.getPasswd());
        ObjectOutputStream os = null;
        try {
			os = new ObjectOutputStream(new FileOutputStream("f:/user.txt"));
			os.writeObject(user);  //将User对象写进文件
			os.flush();
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        //在反序列化之前改变username的值
        user.setUsername("长春理工大学");
        ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(new FileInputStream("f:/user.txt"));
			user = (User) is.readObject();
			System.out.println("\nread after Serializable: ");
	        System.out.println("username: " + user.getUsername());
	        System.err.println("password: " + user.getPasswd());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
	}
}

class User implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;  
    
    //static修饰的变量，在反序列化过程中, 反回的是JVM中对应的static变量的当前的值,而不是序列化时候的值
    public static String username;
    
    //transient修饰的变量，不能被序列化
    private transient String passwd;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasswd() {
        return passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
