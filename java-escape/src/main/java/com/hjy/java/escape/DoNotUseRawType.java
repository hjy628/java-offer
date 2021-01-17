package com.hjy.java.escape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: hjy
 * @Date: 2020/12/17 14:01
 * @Description: <h1>不要使用原始类型</h1>
 */

public class DoNotUseRawType {


    /**
     * <h2>简单使用原始类型</h2>
     */
    private static void simpleExample(){
        List data = new ArrayList();
        data.add("hwshop");
        data.add(18);
        data.add("hello world");

        data.forEach(System.out::println);

        data.forEach(d->{
            if (d instanceof String && ((String)d).equals("hello world")){     //java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
                System.out.println(data.indexOf(d));
            }
        });

    }

    /**
     * <h2>优化使用原始类型</h2>
     */
    private static void optmizeUse(){
/*
        List<Object> data = new ArrayList();
        data.add("hwshop");
        data.add(18);
        data.add("hello world");

        data.forEach(System.out::println);*/


        List<People> data  = new ArrayList<>();


    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class People{
            private String name;
            private Integer age;
            private String signature;
    }

    public static void main(String[] args) {

//        List<String> list = null;

//        List list = null;

        simpleExample();


    }


}
