package com.hjy.java.escape.reflect;

/**
 * @auther: hjy
 * @Date: 2020/12/17 14:42
 * @Description: <h1>People对象</h1>
 */

public class People {

    public String people(String hello) {
        return People.class.getName()+" : "+hello;
    }
}
