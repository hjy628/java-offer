package com.hjy.java.escape.synchronized_;

/**
 * @auther: hjy
 * @Date: 2020/12/17 20:39
 * @Description:
 */

public class MainActive implements Runnable{

    private int value = 0;


    @Override
    public synchronized void run() {
            String name = Thread.currentThread().getName();

            while (true){

                if (value<1000){
                    System.out.println(name+" start: "+value);
                    value++;
                    System.out.println(name+" done: "+value);
                }else {
                    break;
                }
            }
    }
}
