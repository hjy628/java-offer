package com.hjy.spring.escape.use_jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @auther: hjy
 * @Date: 2021/1/12 16:42
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties({"couponCode","status"})
@JsonSerialize(using = CouponSerialize.class)
public class Coupon {

//    @JsonIgnore
    private int id;

//    @JsonProperty("user")
    private Long userId;

    private String couponCode;

//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    private Date assignedTime;

    private CouponStatus status;

    private CouponTemplate template;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponTemplate{
        private String name;

        private String logo;

    }


    public static Coupon fake(){

        return new Coupon(1,100L,"123456",new Date(),CouponStatus.USABLE,new CouponTemplate("停车券","wechat"));

    }


}
