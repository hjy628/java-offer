package com.hjy.java.escape.try_with_resources;

/**
 * @auther: hjy
 * @Date: 2020/12/15 16:12
 * @Description:
 */

public class AutoClose implements AutoCloseable{
    @Override
    public void close() {
        System.out.println(">>>close()");
        throw new RuntimeException("Exception in close()");
    }

    public void work() throws MyException{
        System.out.println(">>> work()");
        throw new MyException("Exception in work()");

    }


}
