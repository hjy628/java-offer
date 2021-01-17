package com.hjy.java.escape.abstract_interface;

/**
 * @auther: hjy
 * @Date: 2020/12/16 20:03
 * @Description: <h1>没一个worker最基本的属性</h1>
 */

public abstract class BaseWork {

    /**起床时间*/
    protected int wakeupTime = 8;

    /**上班打卡*/
    protected abstract void voidclockIn();


    /**下班打卡*/
    protected abstract void voidclockOut();


}
