package com.hjy.java.escape.synchronized_;

/**
 * @auther: hjy
 * @Date: 2020/12/17 20:28
 * @Description: <h1>对value进行减法操作</h1>
 */

public class Active implements Runnable{

    private int value = 1000;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        while (true){

            if (value > 0){
                System.out.println(name+" start: "+value);
                value--;
                System.out.println(name+" down: "+value);
            }else {
                break;
            }
        }

    }
}
