package com.hjy.java.escape.lombok_;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @auther: hjy
 * @Date: 2020/12/16 17:47
 * @Description:  需要加入 @EqualsAndHashCode(callSuper = true)
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppleComputer extends Computer{

    private long price;
    private String color;

    public AppleComputer(Integer id, String name, long price, String color) {
        super(id, name);
        this.price = price;
        this.color = color;
    }
}
