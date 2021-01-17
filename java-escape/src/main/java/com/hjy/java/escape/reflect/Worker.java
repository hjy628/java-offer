package com.hjy.java.escape.reflect;

/**
 * @auther: hjy
 * @Date: 2020/12/17 14:44
 * @Description: <h1>继承自People的Worker对象</h1>
 */

public class Worker extends People{

    public String worker(String hello){
        return Worker.class.getName()+" : "+hello;
    }

}
