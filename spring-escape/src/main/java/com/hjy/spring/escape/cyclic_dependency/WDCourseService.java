package com.hjy.spring.escape.cyclic_dependency;

/**
 * @auther: hjy
 * @Date: 2020/12/19 17:07
 * @Description:
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class WDCourseService {

//    private final HJYJavaService javaService;
//
//    @Autowired
//    public WDCourseService(HJYJavaService javaService) {
//        this.javaService = javaService;
//    }
//
//    @Autowired
//    private HJYJavaService javaService;

    private HJYJavaService javaService;

    //@setter注入
    @Autowired
    public void setJavaService(HJYJavaService javaService){
        this.javaService = javaService;
    }


    public void wdCourse(){
        javaService.hjyJava();
    }


}
