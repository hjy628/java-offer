package com.hjy.java.escape.reflect;

/**
 * @auther: hjy
 * @Date: 2020/12/17 14:45
 * @Description: <h1>继承自Worker的Boss对象</h1>
 */

public class Boss extends Worker {

    public String boss(String hello){
        return Boss.class.getName()+" : "+hello;
    }

    public String numeric(int age){
        return Boss.class.getName()+" ： "+age;
    }

}
