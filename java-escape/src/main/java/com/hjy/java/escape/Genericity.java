package com.hjy.java.escape;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: hjy
 * @Date: 2020/12/17 10:52
 * @Description:  <h1>理解范型</h1>
 */

public class Genericity {


    /**
     * <h2>简单使用范型</h2>
     * @throws Exception
     */
    private static void easyUse() throws Exception{

        List<String> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
/*
        System.out.println(left.getClass());
        System.out.println(left.getClass() == right.getClass());*/


        List<Integer> list = new ArrayList<>();
            list.add(1);
            list.getClass().getMethod("add", Object.class).invoke(list,"str");
            list.getClass().getMethod("add", Object.class).invoke(list,2.3);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }


    /**
     * <h2>范型是先检查，再编译</h2>
     */
    private static void checkAndCompile(){
        ArrayList<String> list = new ArrayList<>();
        list.add("abcd");
//        list.add(342);    //编译时即进行检查，会报错

    }


    /**
     * <h2>范型不支持继承</h2>
     */
    private static void genericityCanNotExtend(){

        //第一类错误
//        ArrayList<String> first = new ArrayList<Object>();      //会报编译错

        //第一类错误的展开形式
        /*ArrayList<Object> list1 = new ArrayList<>();
        list1.add(new Object());
        ArrayList<String> list2 = list1;*/

        //第二类错误
//        ArrayList<Object> second = new ArrayList<String>();  //报编译错误

        //第二类错误的展开形式
        /*ArrayList<String> list1 = new ArrayList<>();
        list1.add(new String());
        ArrayList<Object> list2 = list1;
        */

    }

    /**
     * <h2>范型类型变量不能是基本数据类型</h2>
     */
    private static void baseTypeCanNotUseGenericity(){
//        List<int> invalid = new ArrayList<>();

    }


    /**
     * 范型的类型参数只能是类类型，不能是简单类型
     * @param values
     * @param <T>
     */
    private static <T> void doingSomething(T...values){
            for (T value:values){
                System.out.println(value);
            }
    }

    public static void main(String[] args) throws Exception{
//        easyUse();

        Integer[] ints1 = new Integer[]{1,2,3};

        int[] ints2 = new int[]{1,2,3};

        doingSomething(ints1);
        System.out.println("----------------");
        doingSomething(ints2);



    }





}
