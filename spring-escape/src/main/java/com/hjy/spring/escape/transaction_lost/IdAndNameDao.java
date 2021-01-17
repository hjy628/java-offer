package com.hjy.spring.escape.transaction_lost;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @auther: hjy
 * @Date: 2020/12/27 21:54
 * @Description:
 */

public interface IdAndNameDao  extends JpaRepository<IdAndName,Long> {



}
