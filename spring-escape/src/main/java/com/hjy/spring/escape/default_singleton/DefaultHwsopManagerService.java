package com.hjy.spring.escape.default_singleton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: hjy
 * @Date: 2020/12/19 15:17
 * @Description:
 */

@Slf4j
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)  //原型模式Bean
public class DefaultHwsopManagerService {

    private List<String> mchs = null;

    @PostConstruct
    public void init(){
        log.info("Coming In DefaultHwsopManagerService init!");
        this.mchs = new ArrayList<>(100);

    }

    public void addMchs(String mch){
        this.mchs.add(mch);
    }

    public int mchCount(){
        return this.mchs.size();
    }

    public List<String> getMchs(){
        return this.mchs;
    }

}
