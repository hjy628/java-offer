package com.hjy.offer.base;

/**
 * @auther: hjy
 * @Date: 2020/11/30 20:18
 * @Description:
 */

public class EqualsTest {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = s1;
        String s5 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        System.out.println("== comparison comparison :  " + (s1  == s5));
        System.out.println("== comparison comparison :  " + (s1  == s2));
        System.out.println("Using equals method : " + s1.equals(s2));
        System.out.println("== comparison : " + s3 == s4);
        System.out.println("Using equals method : " + s3.equals(s4));
    }



}
