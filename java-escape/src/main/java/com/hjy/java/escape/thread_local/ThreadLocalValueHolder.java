package com.hjy.java.escape.thread_local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther: hjy
 * @Date: 2020/12/18 17:55
 * @Description: <h1>在线程池中使用ThreadLocal</h1>
 */

public class ThreadLocalValueHolder {

    private static final ThreadLocal<Integer> holder = ThreadLocal.withInitial(()->0);

    public static int getValue(){
        return holder.get();
    }

    public static void remove(){
        holder.remove();
    }

    public static void increment(){
        holder.set(holder.get()+1);
    }


    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 15; i++) {
            executor.execute(()->{
              /*  long threadId = Thread.currentThread().getId();
                int before = getValue();
                increment();
                int after = getValue();

//                System.out.println("Before: "+before + " After: "+after);
                System.out.println("threadId:"+ threadId +"  Before: "+before + " After: "+after);*/

              try {
                  long threadId = Thread.currentThread().getId();
                  int before = getValue();
                  increment();
                  int after = getValue();

//                System.out.println("Before: "+before + " After: "+after);
                  System.out.println("threadId:"+ threadId +"  Before: "+before + " After: "+after);
              }finally {
                  remove();
              }

            });
        }


        executor.shutdown();
    }


}
