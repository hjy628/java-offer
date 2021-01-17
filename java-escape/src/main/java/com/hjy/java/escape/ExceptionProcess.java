package com.hjy.java.escape;

/**
 * @auther: hjy
 * @Date: 2020/12/14 20:27
 * @Description:  <h1>Java异常处理</h1>
 */

public class ExceptionProcess {

    private static class User{}


    /**
    * <h2>Java异常本质 --抛出异常</h2>
    * */
    private void throwException(){
            User user = null;

            //...
        if (null==user){
            throw new NullPointerException();
        }
    }

    /**
     * <h2>不能捕获空指针异常</h2>
     *
     * */
    private void canNotCatchNpeException(){
        try {
            throwException();
        }catch (ClassCastException cce){
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        }
    }


    /**
     * <h2>能够捕获空指针异常</h2>
     *
     * */
    private void canCatNpeException(){
        try {
            throwException();
        }catch (ClassCastException cce){
            System.out.println(cce.getMessage());
            System.out.println(cce.getClass().getName());
        }catch (NullPointerException npe){
            System.out.println(npe.getMessage());
            System.out.println(npe.getClass());
        }
    }


    public static void main(String[] args) {

        ExceptionProcess exceptionProcess = new ExceptionProcess();
        exceptionProcess.canCatNpeException();
        exceptionProcess.canNotCatchNpeException();


    }


}
