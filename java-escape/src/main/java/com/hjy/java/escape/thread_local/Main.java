package com.hjy.java.escape.thread_local;

/**
 * @auther: hjy
 * @Date: 2020/12/18 17:44
 * @Description:  <h1>ThreadLocal的使用和对它的理解</h1>
 */

public class Main {

    /**
     * <h2>ThreadLocal不支持继承</h2>
     */
    private static void threadLocalCanNotInherit(){
            ThreadLocal<String> name = new ThreadLocal<>();
            name.set("hsshop");

            Thread sub = new Thread(()->  System.out.println("Name in Sub: "+ name.get()));
            sub.start();

        System.out.println("Name in Main: "+ name.get());
    }



    public static void main(String[] args) {
/*
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                DoCompetition competition = new DoCompetition();
                competition.code();
                competition.config();
                competition.print();
            },"Thread-"+(i+1)).start();
        }*/

        threadLocalCanNotInherit();

    }

}
