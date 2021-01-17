package com.hjy.spring.escape.cyclic_dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @auther: hjy
 * @Date: 2020/12/19 17:06
 * @Description:
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class HJYJavaService {


//    private final WDCourseService courseService;
//
//    @Autowired
//    public HJYJavaService(WDCourseService courseService) {
//        this.courseService = courseService;
//    }

//
//
//    @Autowired
//    private WDCourseService courseService;

    //setter注入
    private WDCourseService courseService;

    @Autowired
    public void setCourseService(WDCourseService courseService){
        this.courseService = courseService;
    }

    public void hjyJava(){
        System.out.println("HJYJavaService");
    }

}
