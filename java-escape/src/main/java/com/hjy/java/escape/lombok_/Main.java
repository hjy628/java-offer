package com.hjy.java.escape.lombok_;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @auther: hjy
 * @Date: 2020/12/16 17:36
 * @Description:<h1>lombok工具的使用及需要避免的坑</h1>
 */

public class Main {

    private static void singleAlphabetHump() throws Exception{
            ObjectMapper mapper = new ObjectMapper();
            Personal personal = new Personal();
            personal.setIPhone("11");

        System.out.println(mapper.writeValueAsString(personal));  //{"userName":null,"name":null,"iphone":"11"}

         String jsonStr1 = "{\"userName\":\"wangdan\",\"name\":\"wangdan-hw\",\"iphone\":\"11\"}";
        Personal personal2 = mapper.readValue(jsonStr1,Personal.class);      // at [Source: (String)"{"userName":"wangdan","name":"wangdan-hw","iPhone":"11"}"; line: 1, column: 53] (through reference chain: com.hjy.java.escape.lombok_.Personal["iPhone"])
        System.out.println(personal2);
        String jsonStr = "{\"userName\":\"wangdan\",\"name\":\"wangdan-hw\",\"iPhone\":\"11\"}";
        Personal personal1 = mapper.readValue(jsonStr,Personal.class);      // at [Source: (String)"{"userName":"wangdan","name":"wangdan-hw","iPhone":"11"}"; line: 1, column: 53] (through reference chain: com.hjy.java.escape.lombok_.Personal["iPhone"])
        System.out.println(personal1);

    }

    private static void equalsAndHashCodeBug(){
        AppleComputer computer1  = new AppleComputer(1,"Macbook Pro",12555L,"white");
        AppleComputer computer2  = new AppleComputer(2,"Macbook Air",12555L,"white");
        System.out.println(computer1.equals(computer2));
    }

    public static void main(String[] args) throws Exception{
//        singleAlphabetHump();

        equalsAndHashCodeBug();

    }

}
