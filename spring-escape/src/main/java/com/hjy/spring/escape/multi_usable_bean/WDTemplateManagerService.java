package com.hjy.spring.escape.multi_usable_bean;

import org.springframework.stereotype.Service;

/**
 * @auther: hjy
 * @Date: 2020/12/19 16:32
 * @Description:
 */
@Service
public class WDTemplateManagerService implements ITemplageManagerService{
    @Override
    public void print() {
        System.out.println("WDTemplateManagerService");
    }
}
