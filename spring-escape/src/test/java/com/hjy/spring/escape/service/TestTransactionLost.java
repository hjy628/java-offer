package com.hjy.spring.escape.service;

import com.hjy.spring.escape.transaction_lost.CustomeException;
import com.hjy.spring.escape.transaction_lost.ISpringTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther: hjy
 * @Date: 2020/12/27 22:04
 * @Description:
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestTransactionLost {

    @Autowired
    private ISpringTransaction springTransaction;

    @Test
    public void testCatchExceptionCanNotRollback(){
        springTransaction.CatchExceptionCanNotRollback();
    }

    @Test
    public void testNotRuntimeExceptionCanNotRollback()throws CustomeException {
        springTransaction.NotRuntimeExceptionCanNotRollback();
    }


    @Test
    public void testRuntimeExceptionCanRollback() {
        springTransaction.RuntimeExceptionCanRollback();
    }



    @Test
    public void testAssignExceptionCanRollback() throws CustomeException{
        springTransaction.AssignExceptionCanRollback();
    }


    @Test
    public void testNonTransactionalCanNotRollback() throws CustomeException{
        springTransaction.NonTransactionalCanNotRollback();
    }



}
