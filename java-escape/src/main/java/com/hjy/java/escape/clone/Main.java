package com.hjy.java.escape.clone;

import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2020/12/17 17:10
 * @Description: <h1>理解深拷贝和浅拷贝</h1>
 */

public class Main {

    private static void canNotClone() throws CloneNotSupportedException{
            Main main = new Main();
            Object cloned = main.clone();       //java.lang.CloneNotSupportedException: com.hjy.java.escape.clone.Main
    }

    private static void copyTest(){

        Worker worker1 = new Worker("wangdan",18,"w","北京大学","2009");
        System.out.println("原始对象： "+worker1.getEducationInfo().getSchool());
        Worker worker2 = (Worker)worker1.clone();
        System.out.println("拷贝对象： "+worker2.getEducationInfo().getSchool());

        worker2.getEducationInfo().setSchool("上海交通大学");
        System.out.println("原始对象： "+worker1.getEducationInfo().getSchool());
        System.out.println("拷贝对象： "+worker2.getEducationInfo().getSchool());



    }

    public static void main(String[] args)  throws CloneNotSupportedException{
//        canNotClone();
        copyTest();
    }

}
