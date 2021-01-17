package com.hjy.java.escape;

import java.util.Optional;

/**
 * @auther: hjy
 * @Date: 2020/12/14 20:07
 * @Description:<h1>学会Optional,规避空指针异常</h1>
 */

public class OptionalUsage {

    public static class User{
        private String name;

        public String getName() {
            return name;
        }
    }

    private static void isUserEqualNull(){

        User user = null;
        if (user!=null){
            System.out.println("User is not null");
        }else {
            System.out.println("User is null");
        }



        Optional<User> optional = Optional.empty();
        if (optional.isPresent()){
            System.out.println("User is not null");
        }else {
            System.out.println("User is null");
        }

    }

    private static User anoymos(){
        return new User();
    }



    public static void main(String[] args) {

        //没有意义的使用方法
        isUserEqualNull();


        User user = null;
        Optional<User> optionalUser = Optional.ofNullable(user);
        //存在即返回，空则提供默认值
        optionalUser.orElse(new User());
        //存在即返回，空则由函数去产生  灵活性强，可复用性强
        optionalUser.orElseGet(()->anoymos());
        //存在即返回，否则抛出异常
        optionalUser.orElseThrow(RuntimeException::new);

        //存在才去做相应的处理
        optionalUser.ifPresent(u-> System.out.println(u.getName()));

        //map可以对Optional中的对象执行某种操作，且会返回一个Optional对象
        optionalUser.map(u->u.getName()).orElse("anymos");
        //map可无限级联的
        optionalUser.map(u->u.getName()).map(name->name.length()).orElse(0);




    }


}
