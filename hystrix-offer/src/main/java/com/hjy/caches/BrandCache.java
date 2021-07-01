package com.hjy.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: hjy
 * @Date: 2021/5/13 15:06
 * @Description:
 */

public class BrandCache {

    private static Map<Long, String> brandMap = new HashMap<>();
    static {
        brandMap.put(1L, "Apple");
        brandMap.put(2L, "SamSung");
        brandMap.put(3L, "Motorola");
    }

    /**
     * brandId 获取  brandName
     *
     * @param brandId   品牌id
     * @return   品牌名
     */
    public static String getBrandName(Long brandId) {
        return brandMap.get(brandId);
    }


}
