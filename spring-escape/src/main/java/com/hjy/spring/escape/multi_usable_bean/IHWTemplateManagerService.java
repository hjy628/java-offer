package com.hjy.spring.escape.multi_usable_bean;

import org.springframework.stereotype.Service;

/**
 * @auther: hjy
 * @Date: 2020/12/19 16:31
 * @Description:
 */
@Service
public class IHWTemplateManagerService implements ITemplageManagerService {
    @Override
    public void print() {
        System.out.println("IHWTemplateManagerService");
    }
}
