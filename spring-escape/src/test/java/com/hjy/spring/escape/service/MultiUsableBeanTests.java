package com.hjy.spring.escape.service;

import com.hjy.spring.escape.multi_usable_bean.ITemplageManagerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2020/12/19 16:12
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiUsableBeanTests {

    /*
    @Autowired
    @Qualifier(value = "hwShopRedisTemplate")*/
//    private RedisTemplate redisTemplate;
//    @Resource

    @Autowired
    private RedisTemplate hwShopRedisTemplate;

    @Autowired
    @Qualifier("IHWTemplateManagerService")
    private ITemplageManagerService templageManagerService;


    @Test
    public void testAutowire(){
         assert  hwShopRedisTemplate != null;
        hwShopRedisTemplate.getConnectionFactory().getConnection().flushAll();

        hwShopRedisTemplate.opsForValue().set("name","wangdan");

    }

    @Test
    public void testTemplateManagerService(){

//        assert templageManagerService == null;

        templageManagerService.print();

    }



    @Test
    public void testUseRedisPipeline(){

        //清空Redis服务器中的数据,方便校验测试
        hwShopRedisTemplate.getConnectionFactory().getConnection().flushAll();

        List<Object> resultList = hwShopRedisTemplate.executePipelined(
                new RedisCallback<Object>() {
                    @Override
                    public Object doInRedis(RedisConnection connection) throws DataAccessException {

                        //1.  通过connection打开pipeline
                        connection.openPipeline();;
                        //2.给本次pipeline添加一次要执行的多条命令
                        //2.1 一个set key value的操作
                        byte[] key = "name".getBytes();
                        byte[] value = "hjy".getBytes();
                        connection.set(key,value);

                        //2.2 执行一个错误的命令
                        connection.lPop("sdfhiwuf".getBytes());

                        //2.3 mset操作
                        Map<byte[],byte[]> tuple = new HashMap<>();
                        tuple.put("id".getBytes(),"1".getBytes());
                        tuple.put("age".getBytes(),"18".getBytes());
                        connection.mSet(tuple);


                        //3. 关闭pipeline
//                        connection.closePipeline();       不能手动关闭，不然获取不到结果
                        return null;        //必须返回null,才可以获取到结果
                    }
                }
        );
        resultList.forEach(System.out::println);

    }

}
