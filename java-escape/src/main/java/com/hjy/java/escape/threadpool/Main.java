package com.hjy.java.escape.threadpool;

import java.util.concurrent.ExecutorService;

/**
 * @auther: hjy
 * @Date: 2020/12/18 17:15
 * @Description:<h1>可监控的线程池</h1>
 */

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = ExecutorsUtil.newFixedThreadPool(10,"hwshop-");

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

}
