import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/5/13 17:05
 * @Description:
 */
//@SpringBootTest(classes = RejectTest.class)
//@RunWith(SpringRunner.class)
public class RejectTest {


    @Test
    public void testReject() throws Exception{

        String baseUrl = "http://localhost:8080/getProductInfo?productId=";

        for (int i = 0; i < 25; ++i) {
            new Thread(()-> {
                System.out.println(HttpUtil.get(baseUrl+"-2"));
            }).start();
        }

        // 防止主线程提前结束执行
        TimeUnit.SECONDS.sleep(50);




    }



}
