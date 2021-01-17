package com.hjy.java.escape.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @auther: hjy
 * @Date: 2020/12/18 14:34
 * @Description: <h1>阻塞队列的应用</h1>
 */

public class Main {


    public static void main(String[] args) {

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3,true);


        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        new Thread(producer).start();
        new Thread(consumer).start();
    }


}
