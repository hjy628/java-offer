package com.hjy.java.escape.serialization;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @auther: hjy
 * @Date: 2020/12/16 21:39
 * @Description: <h1>Java Object</h1>
 */
@Getter
@Setter
@ToString
public class People implements Serializable {

    public People() {
    }

    private Long id;

    public People(Long id) {
        this.id = id;
    }
}
