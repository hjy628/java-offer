package com.hjy.java.escape.threadpool;

/**
 * @auther: hjy
 * @Date: 2020/12/18 15:49
 * @Description:    <h1> 读书的任务</h1>
 */

public class Reading implements Runnable{

    private int count;
    private String name;

    public Reading(int count, String name) {
        this.count = count;
        this.name = name;
    }

    @Override
    public void run() {
            while (count>0){

                System.out.println(Thread.currentThread().getName()+" reading "+name);

                try {
                    Thread.sleep(1000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                --count;

            }
    }
}
