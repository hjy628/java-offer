package com.hjy.java.escape.thread_local;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @auther: hjy
 * @Date: 2020/12/18 17:25
 * @Description:<h1></h1>
 */

public class Competition {

    public static ThreadLocal<Material> material = ThreadLocal.withInitial(
            ()->   new Material("初始代码","初始配置")
    );

    @Data
    @AllArgsConstructor
    public static class Material{
        private String code;
        private String config;
    }



}
