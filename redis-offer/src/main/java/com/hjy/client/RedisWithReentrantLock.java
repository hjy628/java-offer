package com.hjy.client;


import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2021/5/13 19:56
 * @Description:
 */

public class RedisWithReentrantLock {


    private ThreadLocal<Map> lockers = new ThreadLocal<>();

    private Jedis jedis;

    public RedisWithReentrantLock(Jedis jedis) {
        this.jedis = jedis;
    }

    private boolean _lock(String key){
        return jedis.set(key,"","nx","ex",5L)!=null;
    }

    private void _unlock(String key){
        jedis.del(key);
    }

    private Map<String, Integer> currentLockers(){
        Map<String, Integer> refs = lockers.get();
        if (refs!=null){
            return refs;
        }
        lockers.set(new HashMap());
        return  lockers.get();
    }


    public boolean lock(String key){
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt!=null){
            refs.put(key,refCnt+1);
            return  true;
        }

        boolean ok = this._lock(key);
        if (!ok){
            return  false;
        }
        refs.put(key,1);
        return true;
    }


    public boolean unlock(String key){
        Map<String, Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt==null){
            return  false;
        }
        refCnt -=1;
        if (refCnt>0){
            refs.put(key,refCnt);
        }else {
            refs.remove(key);
            this._unlock(key);
        }
        return  true;
    }


    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379 );
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis);
        System.out.println(redis.lock("codehole"));
        System.out.println(redis.lock("codehole"));
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));
    }




}
