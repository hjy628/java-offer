package com.hjy.spring.escape.transaction_lost;

/**
 * @auther: hjy
 * @Date: 2020/12/27 21:56
 * @Description: <h1>注解演示接口定义</h1>
 */

public interface ISpringTransaction {

    /**主动捕获了异常，导致事务不能回滚*/
    void CatchExceptionCanNotRollback();


    /**不是unchecked异常，事务不能回滚*/
    void NotRuntimeExceptionCanNotRollback() throws CustomeException;

    /**unchecked异常，可以回滚*/
    void RuntimeExceptionCanRollback();

    /**指定rollbackFor，事务可以回滚*/
    void AssignExceptionCanRollback()throws CustomeException;

    /** 同一个类中，一个不标注事务的方法去调用了标注事务的方法，事务会失效*/
    void NonTransactionalCanNotRollback();

}
