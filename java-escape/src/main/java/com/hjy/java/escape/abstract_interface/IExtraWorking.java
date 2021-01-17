package com.hjy.java.escape.abstract_interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther: hjy
 * @Date: 2020/12/16 20:07
 * @Description: <h1>部分程序员的工作</h1>
 */

public interface IExtraWorking {

    void extraCoding();

    void extraTesting();



    default void config(){
        System.out.println("ExtraWorking for Config");
    }


    static void time(){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
