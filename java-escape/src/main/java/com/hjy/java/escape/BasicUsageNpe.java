package com.hjy.java.escape;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: hjy
 * @Date: 2020/12/14 19:50
 * @Description: <h1>字符串、数组、集合在使用时出现空指针</h1>
 */

public class BasicUsageNpe {

    private static boolean stringEquals(String x,String y){
        return x.equals(y);
    }

    public static class User{
        private String name;
    }

    public static void main(String[] args) {


        //1. 字符串使用equals 可能会报空指针错误!
//        System.out.println(stringEquals("xyz", null));
//        System.out.println(stringEquals(null, "xyz"));


        //2.对象数组new出来，但是元素没有初始化
  /*      User[] users = new User[10];
        for (int i = 0; i != 10 ; ++i) {
            users[i] = new User();
            users[i].name = "hjy-"+i;
        }*/


        //3. List对象addAll 传递null会抛出空指针
      /*  List<User> users = new ArrayList<User>();
        List<User> users_ = null;
        User user = null;
        users.add(user);
        users.addAll(users_);

        */



    }




}
