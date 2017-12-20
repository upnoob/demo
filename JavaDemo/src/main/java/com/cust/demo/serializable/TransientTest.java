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
 * @description 序列化
 * 		记住：对象的序列化是基于字节的，不能使用Reader和Writer等基于字符的层次结构
 * 		虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点就是两个类的序列化ID是否一致
 * 		序列化对象是深复制，地址不同
 * 		使用transient关键字不序列化某个变量，注意读取的时候，读取数据的顺序一定要和存放数据的顺序保持一致。
 *
 * @conclusion 
 * 		1、一旦变量被transient 修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
 * 		2、transient关键字只能修饰成员变量，不能修饰成员变量（本地变量、局部变量），也不能修饰方法和类
 * 		3、如果transient修饰的是用户自定义的类变量，则该类需要实现Serializable接口。
 * 		4、被transient 修饰的变量不能被序列化，一个static修饰的变量不管是否被transient修饰，均不能被序列化。
 * 		5、当一个父类实现序列化，子类自动实现序列化，不需要显式实现Serializable接口。
		6、一个子类实现了 Serializable 接口，它的父类都没有实现 Serializable 接口，要想将父类对象也序列化，就需要让父类也实现Serializable 接口。
			第二种情况中：如果父类不实现 Serializable接口的话，就需要有默认的无参的构造函数。这是因为一个 Java 对象的构造必须先有父对象，才有子对象，反序列化也不例外。在反序列化时，为了构造父对象，只能调用父类的无参构造函数作为默认的父对象。因此当我们取父对象的变量值时，它的值是调用父类无参构造函数后的值。在这种情况下，在序列化时根据需要在父类无参构造函数中对变量进行初始化，否则的话，父类变量值都是默认声明的值，如 int 型的默认是 0，string 型的默认是 null。
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
   //     user.setUsername("长春理工大学");
        ObjectInputStream is = null;
        User user2 = null;
		try {
			is = new ObjectInputStream(new FileInputStream("f:/user.txt"));
			user2 = (User) is.readObject();
			System.out.println("\nread after Serializable: ");
	        System.out.println("username: " + user2.getUsername());
	        System.err.println("password: " + user2.getPasswd());
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
		
		//序列化对象，深复制，是新的对象，地址不同
		System.out.println(user.equals(user2));
		System.out.println(user == user2);
		
        
	}
}

class User implements Serializable {
    private static final long serialVersionUID = 8294180014912103005L;  
    
    //static修饰的变量，在反序列化过程中, 反回的是JVM中对应的static变量的当前的值,而不是序列化时候的值
//    public static String username;
    public String username;
    
    //transient修饰的变量，不能被序列化
//    private transient String passwd;
    private String passwd;
    
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
