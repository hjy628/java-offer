package com.hjy.java.escape.copy_on_write;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: hjy
 * @Date: 2020/12/18 15:14
 * @Description:   <h1>并发读写会抛出异常</h1>
 */

public class TaskPoolProblem {

    private static final List<String> tasks = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException{

        for (int i = 0; i < 10; i++) {
            tasks.add("task-"+i);
        }

        Thread thread = new Thread(()->{

                while (true){
                    tasks.add("task-x");
                }
        });

        thread.setDaemon(true);
        thread.start();

        Thread.sleep(1000L);

        for(String task:tasks){
            System.out.println(task);
        }


    }


}
