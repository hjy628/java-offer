package com.hjy.java.escape.threadpool;

import java.util.concurrent.*;

/**
 * @auther: hjy
 * @Date: 2020/12/18 15:52
 * @Description: <h1>简单使用线程池</h1>
 */

public class EasyUseThreadPool {

    private static void useFixedThreadPool(int threadCount){
        
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        Runnable runnable01 = new Reading(3,"Java编程思想");
        Runnable runnable02 = new Reading(5,"Java核心技术");
        Runnable runnable03 = new Reading(1,"Java并发编程的艺术");
        Runnable runnable04 = new Reading(3,"Spring源码深度解析");
        Runnable runnable05 = new Reading(2,"Spring-cloud实战");

        executorService.execute(runnable01);
        executorService.execute(runnable02);
        executorService.execute(runnable03);
        executorService.execute(runnable04);
        executorService.execute(runnable05);

        executorService.shutdown();
        
    }


    /**
     * <h2>自定义线程池</h2>
     */
    private static void customeThreadPool(){


        ThreadPoolExecutor customeExecutor = new ThreadPoolExecutor(1,
                1,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(2));

        ThreadPoolExecutor customeExecutor2 = new ThreadPoolExecutor(1,
                1,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(2),new CustomRejectHandler());

        for (int i = 0; i < 5; i++) {
            customeExecutor2.execute(new Reading(3,"Java编程思想"));
        }

        customeExecutor2.shutdown();
    }


    private static class CustomRejectHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    try {
                        executor.getQueue().put(r);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
        }
    }


    public static void main(String[] args) {
//        useFixedThreadPool(3);
        customeThreadPool();        // java.util.concurrent.RejectedExecutionException: Task com.hjy.java.escape.threadpool.Reading@17f6480 rejected from java.util.concurrent.ThreadPoolExecutor@2d6e8792[Running, pool size = 1, active threads = 1, queued tasks = 2, completed tasks = 0]
    }


}
