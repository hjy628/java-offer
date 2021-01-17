package com.hjy.spring.escape.service;

import com.hjy.spring.escape.cyclic_dependency.HJYJavaService;
import com.hjy.spring.escape.cyclic_dependency.WDCourseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: hjy
 * @Date: 2020/12/19 17:11
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CyclicDependencyTests {

    @Autowired
    private HJYJavaService javaService;

    @Autowired
    private WDCourseService courseService;


    @Test
    public void testCyclicDependency(){
        javaService.hjyJava();
        courseService.wdCourse();
    }




}
