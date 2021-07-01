package com.hjy.webflux02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @auther: hjy
 * @Date: 2021/6/29 16:52
 * @Description:
 */
@Slf4j
@RestController
public class TestController {


    @GetMapping("/1")
    private String get1(){
        log.info("get1 start");
         String reslut = createStr();
        log.info("get1 end");
        return reslut;
    }

    private String createStr() {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some String";
    }

    @GetMapping("/2")
    private Mono<String> get2(){
        log.info("get2 start");
        Mono<String> reslut =Mono.fromSupplier(()->createStr());
        log.info("get2 end");
        return reslut;
    }


    /**
     * Flux 返回0-n个元素
     * @return
     */
    @GetMapping(value = "/3",produces  = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @GetMapping(value = "/3",produces  ="text/event-stream")
    private Flux<String> flux(){
        log.info("get3 start");
        Flux<String> reslut =Flux.fromStream(IntStream.range(1,15).mapToObj(i->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data--"+i;
        }));
        log.info("get3 end");
        return reslut;
    }



}
