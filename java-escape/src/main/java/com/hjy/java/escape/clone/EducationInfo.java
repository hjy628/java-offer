package com.hjy.java.escape.clone;

import com.hjy.java.escape.serialization.Combo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @auther: hjy
 * @Date: 2020/12/17 17:13
 * @Description: <h1>教育信息</h1>
 */
@Data
@AllArgsConstructor
public class EducationInfo implements /*Cloneable*/  Serializable {

    private String school;

    private String time;

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
