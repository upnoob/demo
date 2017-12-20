package com.cust.demo.serializable;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.text.AbstractDocument.Content;

/**
 * @description 使用Externalizable接口实现序列化
 * @author upnoob
 *
 */
public class ExternalizableTest implements Externalizable{

	private transient String content = "我将被序列化，不管我是否被transient关键字修饰";
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(content); //序列化变量
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		content = (String) in.readObject();
	}

	public static void main(String[] args) throws Exception{
		
		//序列化
		ExternalizableTest et = new ExternalizableTest();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("test")));
		oos.writeObject(et);
		
		
		//在反序列化前改变对象的值
		et.setContent("改变一下我的值");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("test")));
		et = (ExternalizableTest) ois.readObject();
		System.out.println(et.content);
		oos.close();
		ois.close();
	}
}
