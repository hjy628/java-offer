package com.hjy.java.escape.abstract_interface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @auther: hjy
 * @Date: 2020/12/16 20:06
 * @Description: <h1>程序员的基本工作</h1>
 */

public interface IBaseWorking {

    void baseCoding();

    void baseTesting();

    default void config(){
        System.out.println("BaseWorking for Config");
    }


    static void time(){
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }


}
