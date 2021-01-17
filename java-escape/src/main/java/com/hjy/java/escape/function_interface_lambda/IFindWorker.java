package com.hjy.java.escape.function_interface_lambda;

/**
 * @auther: hjy
 * @Date: 2020/12/15 20:25
 * @Description:
 */
@FunctionalInterface
public interface IFindWorker {

    Worker findWorkerById(Long id);

}
