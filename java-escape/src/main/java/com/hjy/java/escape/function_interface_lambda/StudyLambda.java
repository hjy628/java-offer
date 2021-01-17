package com.hjy.java.escape.function_interface_lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @auther: hjy
 * @Date: 2020/12/15 16:44
 * @Description: <h1>lambda表达式的使用</h1>
 */

public class StudyLambda {


    /**
     * <h2>Java8之前创建线程</h2>
     */
    private static void baseUse(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Class Thread run()");
            }
        }).start();
    }



    /**
     * <h2>Java8创建线程</h2>
     */
    private static void easyUseLambda(){
        new Thread(()->System.out.println("Anonymous Class Thread run()")).start();
    }


    /**
     * <h2>按照字符串长度进行排序</h2>
     */
    private static void myCompare(){
        //Java8之前
        List<String> list = Arrays.asList("x","y","z");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1==null){
                    return -1;
                }
                if (o2 == null){
                    return 1;
                }
                return o1.length() - o2.length();
            }
        });


        //Java8使用lambda表达式去实现
        Collections.sort(list,(s1,s2)->{
            if (s1==null){
                return -1;
            }
            if (s2 == null){
                return 1;
            }
            return s1.length() - s2.length();
        });
    }


    /**
     * <h2>理解stream的中间操作和结束操作</h2>
     */
    private static void  howToUseLambda(){
                List<String> names = Arrays.asList("hwshop","wd","hjy");

                List<String> newNames = names.stream()
                        .filter(n->n.startsWith("h"))
                        .map(n->n.toUpperCase()).collect(Collectors.toList());

        System.out.println(newNames);
    }


    /**
     * <h2>Stream和lambda可能导致计算低效</h2>
     */
    private static void badUseLambda(){
        List<String> names = Arrays.asList("hwshop","wd","hjy");

        int longestNameSize = names.stream()
                                .filter(s -> s.startsWith("h"))
                                .mapToInt(String::length)
                                .max()
                                .getAsInt();

        int longest = 0;
        for (String str:names){
            if (str.startsWith("h")){
                int len = str.length();
                longest = Math.max(len,longest);
            }
        }

        System.out.println(longest);
        System.out.println(longestNameSize);
    }




    public static void main(String[] args) {
//        howToUseLambda();
        badUseLambda();
    }





}
