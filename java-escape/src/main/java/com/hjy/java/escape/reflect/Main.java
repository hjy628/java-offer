package com.hjy.java.escape.reflect;

import java.lang.reflect.Method;

/**
 * @auther: hjy
 * @Date: 2020/12/17 14:47
 * @Description: <h1>验证Java中的反射机制</h1>
 */

public class Main {


    /**
     * <h2>方法的参数是基本类型，反射获取Method参数类型必须一致</h2>
     * @throws Exception
     */
    private static void reflectDeclaredMethod() throws Exception{

        Class<Boss> clz = Boss.class;
        Method[] methods = clz.getDeclaredMethods();

//        Method method = clz.getDeclaredMethod("numeric",int.class);
        Method method = clz.getDeclaredMethod("numeric",Integer.TYPE);
//        Method method = clz.getDeclaredMethod("numeric",Integer.class); //java.lang.NoSuchMethodException: com.hjy.java.escape.reflect.Boss.numeric(java.lang.Integer)
        System.out.println(method.invoke(clz.newInstance(), 19));

    }


    /**
     * <h2>调用的方法属于对象的父类，getDeclaredMethod() 会抛出异常</h2>
     * @throws Exception
     */
    private static void reflectAcquireClassMethod() throws Exception{

        Class<Boss> clz = Boss.class;

//        Method method = clz.getDeclaredMethod("boss",String.class);
//        Method method = clz.getDeclaredMethod("worker",String.class);   // java.lang.NoSuchMethodException: com.hjy.java.escape.reflect.Boss.worker(java.lang.String)

        Method superMethod = getMethod(clz,"worker",new Class[]{String.class});

        System.out.println(superMethod.invoke(clz.newInstance(), "boss"));

    }


    private static Method getMethod(Class<?> target,String methodName,Class<?>[] argTypes){

        Method method = null;
        try {
            method = target.getDeclaredMethod(methodName,argTypes);
            method.setAccessible(true);
        }catch (NoSuchMethodException e){
            System.out.println("can not get method  "+methodName + " from "+target.getName());
        }
        if (method == null && target != Object.class){
            return getMethod(target.getSuperclass(),methodName,argTypes);
        }
        return method;

    }

    public static void main(String[] args) throws Exception{
//        reflectDeclaredMethod();
        reflectAcquireClassMethod();
    }

}
