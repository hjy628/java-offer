package com.h.interfaces;

/**
 * @auther: hjy
 * @Date: 2021/6/30 14:45
 * @Description: 创建代理类接口
 */

public interface ProxyCreator {

    /**
     *   创建代理类
     * @param type
     * @return
     */
    Object createProxy(Class<?> type);


}
