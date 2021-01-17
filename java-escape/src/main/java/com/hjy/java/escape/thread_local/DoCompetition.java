package com.hjy.java.escape.thread_local;

/**
 * @auther: hjy
 * @Date: 2020/12/18 17:39
 * @Description:
 */

public class DoCompetition {

    public void code(){

        Competition.Material material = Competition.material.get();

        material.setCode(Thread.currentThread().getName());

        Competition.material.set(material);

    }

    public void config(){

        Competition.Material material = Competition.material.get();

        material.setConfig(Thread.currentThread().getName());

        Competition.material.set(material);

    }

    public void print(){
        System.out.println(String.format("Thread name: %s, ThreadLocal hashcode: %s, Instance hashcode: %s,"+
                "Value: %s ",Thread.currentThread().getName(),
                Competition.material.hashCode(),
                Competition.material.get().hashCode(),
                Competition.material.get().toString()));
    }


}
