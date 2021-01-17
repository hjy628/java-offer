package com.hjy.spring.escape.data_se_de;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2021/1/7 17:55
 * @Description: <h1>用于演示时间序列化和反序列化</h1>
 */

@RestController
@RequestMapping("needDate")
public class NeedDateController {


    @GetMapping(value ="param")
    public Map<String, Long> data( @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date){
        Map<String, Long> result = new HashMap<>();
        result.put("timestamp",date.getTime());
        return result;
    }



    @PostMapping(value ="user")
    public Map<String, String> postData(@RequestBody UserInfo userInfo){
        Map<String, String> result = new HashMap<>();
        result.put("id",userInfo.getId().toString());
        result.put("name",userInfo.getName());
        result.put("birthday",String.valueOf(userInfo.getBirthday().getTime()));
        return result;
    }








}
