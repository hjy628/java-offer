package com.hjy.spring.escape.transaction_lost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;

/**
 * @auther: hjy
 * @Date: 2020/12/27 22:02
 * @Description:
 */
@Service
public class SpringTransactionImpl implements ISpringTransaction{

    @Autowired
    private IdAndNameDao idAndNameDao;


    /**
     * 捕获了异常，不会抛出异常，所以不会回滚
     */
    @Override
    @Transactional
    public void CatchExceptionCanNotRollback() {
            try {
                idAndNameDao.save(new IdAndName("wangdan"));
                throw new RuntimeException();
            }catch (Exception ex){
                ex.printStackTrace();

                //手动标记回滚，则会回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
    }

    @Override
    @Transactional
    public void NotRuntimeExceptionCanNotRollback() throws CustomeException {
        try {
            idAndNameDao.save(new IdAndName("wangdan"));
            throw new RuntimeException();
        }catch (Exception ex){
           throw new CustomeException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void RuntimeExceptionCanRollback() {
        idAndNameDao.save(new IdAndName("wangdan"));
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackOn = {CustomeException.class})
    public void AssignExceptionCanRollback() throws CustomeException {
        try {
            idAndNameDao.save(new IdAndName("wangdan"));
            throw new RuntimeException();
        }catch (Exception ex){
            throw new CustomeException(ex.getMessage());
        }
    }

    @Transactional
    public void anotherOneSaveMethod(){
        idAndNameDao.save(new IdAndName("wangdan"));
        throw new RuntimeException();
    }

    @Override
    public void NonTransactionalCanNotRollback() {
        anotherOneSaveMethod();
    }
}
