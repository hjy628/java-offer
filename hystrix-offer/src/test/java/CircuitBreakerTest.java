import cn.hutool.http.HttpUtil;
import com.hjy.HystrixOfferApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2021/5/13 16:29
 * @Description:
 */
@SpringBootTest(classes = CircuitBreakerTest.class)
@RunWith(SpringRunner.class)
public class CircuitBreakerTest {

    @Test
    public void testCircuitBreaker()throws Exception{
        String baseUrl = "http://localhost:8080/getProductInfo?productId=";

        for (int i = 0; i < 30; ++i) {
            System.out.println(HttpUtil.get(baseUrl+"-1"));
        }

        TimeUnit.SECONDS.sleep(5);

        System.out.println("After Sleeping....");

        for (int i = 31; i < 100; ++i) {
            //传入1，走服务正常调用
            HttpUtil.get(baseUrl+"1");
        }

    }

}
