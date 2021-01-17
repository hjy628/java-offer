package com.hjy.spring.outer;

import org.springframework.stereotype.Component;

/**
 * @auther: hjy
 * @Date: 2020/12/18 23:36
 * @Description: <h1>默认扫描机制之外定义的Bean</h1>
 */
@Component
public class Outer {

    public void print(){
        System.out.println("This is An Outer Class");
    }

}
