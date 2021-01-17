package com.hjy.java.escape.abstract_interface;

/**
 * @auther: hjy
 * @Date: 2020/12/16 20:35
 * @Description: <h1>员工类</h1>
 */

public class Worker extends BaseWork implements IBaseWorking,IExtraWorking{
    @Override
    protected void voidclockIn() {
        System.out.println("voidclockIn");
    }

    @Override
    protected void voidclockOut() {
        System.out.println("voidclockOut");
    }

    @Override
    public void baseCoding() {
        System.out.println("baseCoding");
    }

    @Override
    public void baseTesting() {
        System.out.println("baseTesting");
    }

    @Override
    public void config() {
            //定义自己的config
    }

    @Override
    public void extraCoding() {
        System.out.println("extraCoding");
    }

    @Override
    public void extraTesting() {
        System.out.println("extraTesting");
    }
}
