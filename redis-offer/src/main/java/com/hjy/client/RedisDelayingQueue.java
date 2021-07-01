package com.hjy.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/5/13 20:31
 * @Description:
 */

public class RedisDelayingQueue<T> {

    static class TaskItem<T>{
        public String id;
        public T msg;
    }


    //fastjson序列化对象中存在generic类型时，需要使用TypeReference
    private Type taskType = new TypeReference<TaskItem<T>>(){}.getType();
    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg){
        TaskItem task = new TaskItem();
        task.id = UUID.randomUUID().toString();  //分配唯一的uuid
        task.msg = msg;

        String s = JSON.toJSONString(task);

        jedis.zadd(queueKey,System.currentTimeMillis()+5000,s); //塞入延时队列，5S后再试

            }

    public void loop(){
        while (!Thread.interrupted()){
            //只取一条
            Set<String> values = jedis.zrangeByScore(queueKey,0,System.currentTimeMillis(),0,1);
            if (values.isEmpty()){
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                }catch (InterruptedException e){
                    break;
                }
                continue;
            }

            String s = values.iterator().next();
            if (jedis.zrem(queueKey,s)>0){  //抢到了
                TaskItem taskItem = JSON.parseObject(s,taskType);   //fastjson反序列化
                this.handleMsg((T) taskItem.msg);

            }
        }
      }


    public void handleMsg(T msg) {
        System.out.println(msg);
    }


    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1",6379 );
        RedisDelayingQueue queue = new RedisDelayingQueue(jedis,"q-demo");

        Runnable target;
        Thread producer = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                queue.delay("qhjy"+i);
            }
        });


        Thread consumer = new Thread(()->{
            queue.loop();
        });


        producer.start();
        consumer.start();

        try {
            producer.join();
            TimeUnit.SECONDS.sleep(6);
            consumer.interrupt();
            consumer.join();
        }catch (InterruptedException e){

        }


    }



}
