package com.cust.demo.serializable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList里的elementData数组定义方式 ：  transient Object[] elementData; // non-private to simplify nested class access
 *  public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
    探讨：ArrayList对象是如果持久化下来的
             writeObject ---> writeObject0 --->writeOrdinaryObject--->writeSerialData--->invokeWriteObject
             通过反射方式调用

 @conclusion
     1、如果一个类想被序列化，需要实现Serializable接口。否则将抛出NotSerializableException异常，这是因为，在序列化操作过程中会对类型进行检查，要求被序列化的类必须属于Enum、Array和Serializable类型其中的任何一种。

     2、在变量声明前加上transient/static关键字，可以阻止该变量被序列化到文件中。

     3、在类中增加writeObject 和 readObject 方法可以实现自定义序列化策略
 * Created by upnoob on 2017/12/20.
 */
public class ArrayListSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<String> stringList = new ArrayList<String>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hiollis");
        stringList.add("chuang");
        System.out.println("init StringList" + stringList);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("stringlist")));
        oos.writeObject(stringList);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("stringlist")));
        List<String> newlist = (List<String>) ois.readObject();
        ois.close();

        System.out.println("new list " + newlist);
    }
}
