package com.hjy.java.escape.serialization;

import java.io.*;

/**
 * @auther: hjy
 * @Date: 2020/12/16 21:40
 * @Description:<h1>序列化和反序列化</h1>
 */

public class Main {

    private static void testSerializablePeople() throws Exception{

        //序列化的步骤

        //用于存储序列化的文件
        File files = new File("/tmp/people_10.java_");
        People p = new People(10L);

        //创建一个输出流
        OutputStream out;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(files));
        //输出可序列化对象
        oos.writeObject(p);

        //关闭输出流
        oos.close();


        //反序列化的步骤

        //创建一个输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files));
        //得到序列化的对象
        Object newPerson = ois.readObject();;

        //关闭输入流
        ois.close();

        System.out.println(newPerson);

    }


    /**
     * <h2>子类实现序列化，父类不实现序列化</h2>
     * @throws Exception
     */
    private static void testSerializableWorker()throws Exception{
        File files = new File("/tmp/worker_10.java_");
        Worker p = new Worker(10L,"hwshop",22);


        //创建一个输出流
        OutputStream out;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(files));
        //输出可序列化对象
        oos.writeObject(p);

        //关闭输出流
        oos.close();


        //反序列化的步骤

        //创建一个输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files));
        //得到序列化的对象
        Object newWorker = ois.readObject();    // java.io.InvalidClassException: com.hjy.java.escape.serialization.Worker; no valid constructor

        //关闭输入流
        ois.close();

        System.out.println(newWorker);
    }

    private static void testSerializableCombo()throws Exception{
        File files = new File("/tmp/combo_10.java_");
        Combo p = new Combo(1,new People(10L));


        //创建一个输出流
        OutputStream out;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(files));
        //输出可序列化对象
        oos.writeObject(p);

        //关闭输出流
        oos.close();


        //反序列化的步骤

        //创建一个输入流
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files));
        //得到序列化的对象
        Object newCombo = ois.readObject(); //即使提供了无参构造函数，仍不能序列化java.io.NotSerializableException: com.hjy.java.escape.serialization.People

        //关闭输入流
        ois.close();

        System.out.println(newCombo);
    }


    /**
     * <h2>同一个对象多次序列化的问题</h2>
     * @throws Exception
     */
    private static void sameObjectRepeatedSerialization()throws Exception{
        
        File file = new File("/tmp/people_more_.java_");
        People p = new People(10L);

        OutputStream out;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

        oos.writeObject(p);
        oos.writeObject(p);

        p.setId(20L);

        oos.writeObject(p);

        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object people1 = ois.readObject();
        Object people2 = ois.readObject();
        Object people3 = ois.readObject();
        ois.close();

        System.out.println(((People) people1).getId());
        System.out.println(((People) people2).getId());
        System.out.println(((People) people3).getId());


    }

    public static void main(String[] args) throws Exception{

//        testSerializablePeople();
//        testSerializableWorker();
//        testSerializableCombo();
            sameObjectRepeatedSerialization();
    }



}
