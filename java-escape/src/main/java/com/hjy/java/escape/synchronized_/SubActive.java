package com.hjy.java.escape.synchronized_;

/**
 * @auther: hjy
 * @Date: 2020/12/17 20:47
 * @Description:
 */

public class SubActive extends MainActive{

    private int value = 1000;

    @Override
    public synchronized void run() {
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
