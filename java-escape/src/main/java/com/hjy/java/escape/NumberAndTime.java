package com.hjy.java.escape;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @auther: hjy
 * @Date: 2020/12/15 16:24
 * @Description:    <h1>数值计算和时间计算</h1>
 */
@SuppressWarnings("all")
public class NumberAndTime {


    /**
     * <h2>Scale需要与小数位匹配</h2>
     */
    private static void scaleProblem(){

        BigDecimal decimal = new BigDecimal("12.333");
//        BigDecimal result = decimal.setScale(2);        //Exception in thread "main" java.lang.ArithmeticException: Rounding necessary
//        BigDecimal result1 = decimal.setScale(12);        //Exception in thread "main" java.lang.ArithmeticException: Rounding necessary
//        System.out.println(result);
//        System.out.println(result1);



        BigDecimal result = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(result);
    }


    /**
     * <h2>BigDecimal 做除法时出现除不尽的情况</h2>
     */
    private static void divideProblem(){
//        System.out.println(new BigDecimal(30).divide(new BigDecimal(7)));   //java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        System.out.println(
                new BigDecimal(30).divide(new BigDecimal(7),2,BigDecimal.ROUND_HALF_UP)
            );
    }


    /**
     * <h2>SimpleDateFormat 可以解析大于/等于它的时间精度</h2>
     * @throws Exception
     */
    private static void formatPrecision()throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time_x = "2020-12-15 10:30:00";
        String time = "2020-12";        //java.text.ParseException: Unparseable date: "2020-12"

        System.out.println(sdf.parse(time_x));
        System.out.println(sdf.parse(time));

    }

    /**
     * <h2>SimpleDateFormat 存在线程安全问题</h2>
     * @throws Exception
     */
    private static void threadSafety(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        TimeUnit unit;
        BlockingQueue workQueue;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10
                ,100 , 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(1000));

        while (true){

            threadPoolExecutor.execute(()->{
                String dateString = "2020-12-15 10:30:00";
                try {
                    Date parse = sdf.parse(dateString);     // java.lang.NumberFormatException: For input string: ".E0"
                    String dateString2 = sdf.format(parse);
                    System.out.println(dateString.equals(dateString2));
                }catch (ParseException ex){
                    ex.printStackTrace();
                }
            });

        }

    }




    /**
     *  <h2>精度问题导致比较结果和预期的不一致</h2>
     */
    private static void equalProblem(){
        BigDecimal bd1 = new BigDecimal("0");
        BigDecimal bd2 = new BigDecimal("0.00");

        System.out.println(bd1.equals(bd2));
        System.out.println(bd1.compareTo(bd2) == 0);
    }

    public static void main(String[] args) throws Exception{
//            scaleProblem();

//        divideProblem();

//        equalProblem();

//        formatPrecision();

        threadSafety();
    }






}
