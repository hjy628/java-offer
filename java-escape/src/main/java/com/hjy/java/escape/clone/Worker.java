package com.hjy.java.escape.clone;

import lombok.Data;

import java.io.*;

/**
 * @auther: hjy
 * @Date: 2020/12/17 17:14
 * @Description: <h1>员工对象</h1>
 */
@Data
public class Worker implements /*Cloneable*/ Serializable {
    private String name;
    private Integer age;
    private String gender;

    private EducationInfo educationInfo;

    public Worker(String name, Integer age, String gender, String school,String time) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.educationInfo = new EducationInfo(school, time);
    }


    //浅拷贝
/*
    @Override
    protected Object clone() {
        try {
            return super.clone();

        }catch (CloneNotSupportedException e){
            return null;
        }
    }*/

/*
    @Override
    protected Object clone()  {
*//*
        //第一种方式  效率不高
            Worker worker = new Worker(name,age,gender,educationInfo.getSchool(),educationInfo.getTime());
            return worker;*//*

        //第二种方式   多层克隆的问题
        try {
            Worker worker = (Worker)super.clone();
            worker.educationInfo = (EducationInfo)educationInfo.clone();
            return worker;
        }catch (CloneNotSupportedException ex){
            return null;
        }


    }*/


    @Override
    public Worker clone(){
        Worker worker = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            //将流序列化成对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            worker = (Worker) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return worker;
    }



}
