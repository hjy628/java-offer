package com.hjy.java.escape;

import com.google.common.base.Enums;

import java.util.*;

/**
 * @auther: hjy
 * @Date: 2020/12/14 20:48
 * @Description:  <h1>编码中常见的异常</h1>
 */
@SuppressWarnings("all")
public class GeneralException {

    public static class User{
        public User() {
        }

        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private static final Map<String, StaffType> typeIndex = new HashMap<>(StaffType.values().length);

    static {
        for (StaffType value:StaffType.values()){
            typeIndex.put(value.name(),value);
        }
    }

    public static class Manager extends User{

    }

    public static class Worker extends User{

    }

    private static StaffType enumFind(String type){
//        return StaffType.valueOf(type);


        //1.最普通、简单的实现
/*        try {
            return StaffType.valueOf(type);
        }catch (IllegalArgumentException ex){
            return null;
        }*/

        //2.改进第一种实现,但是效率不高
        /*for (StaffType value:StaffType.values()){
            if (value.name().equals(type)){
                return value;
            }
        }
        return null;
*/
        //3.使用静态的Map索引,只有一次枚举循环的过程
//        return typeIndex.get(type);

        //4.使用Google Guava Enums,需要相关依赖
        return Enums.getIfPresent(StaffType.class,type).orNull();

    }


    private static void concurrentModificationException(ArrayList<User> users ){
        //直接使用for循环会触发并发修改异常
        /*for (User user : users) {
            if (user.getName().equals("hjy")){
                users.remove(user);
            }
        }
*/
        //使用迭代器则没有问题
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()){
            User user = iter.next();
            if (user.getName().equals("hjy")){
                iter.remove();
            }
        }
        //先调用remove再调用next方法，则仍然会触发并发修改异常

    }


    public static void main(String[] args) {
        /*
        //1.并发修改异常
        ArrayList<User> users = new ArrayList<>(
                Arrays.asList(new User("qiye"),new User("hjy"))
        );
        concurrentModificationException(users);*/

        //2.类型转换异常
        User user1 = new Manager();
        User user2 = new Worker();

   /*     Manager m1 = (Manager)user1;
        Manager m2 = (Manager)user2;  //类型转换异常  java.lang.ClassCastException
*/

   /*
        System.out.println(user2.getClass().getName());

        System.out.println(user2 instanceof  Manager);*/


        //3.枚举查找异常
        System.out.println(enumFind("RD"));
        System.out.println(enumFind("ABC"));        //java.lang.IllegalArgumentException: No enum constant

    }



}
