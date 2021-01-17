package com.hjy.java.escape.try_with_resources;

import org.checkerframework.checker.units.qual.A;

import java.io.*;

/**
 * @auther: hjy
 * @Date: 2020/12/15 15:53
 * @Description:    <h1>解决使用try finally的资源泄漏隐患</h1>
 */

public class Main {

    /**
     * <h2> 传统的方式实现对资源的关闭</h2>
     * @return
     * @throws IOException
     */
    private String traditionalTryCatch() throws IOException{

        //1.单一资源的关闭
 /*       String line  = null;
        BufferedReader br = new BufferedReader(new FileReader("/etc/passwd"));
        try {
            line =  br.readLine();
        }finally {
            br.close();
        }
        return line;
*/

        //2.多个资源的关闭
        InputStream in = new FileInputStream("");
        try {
            //第二个资源
            OutputStream out = new FileOutputStream("");
            try {
                byte[] buf = new byte[1024];
                int n ;

                while ((n= in.read(buf))>=0) {
                    out.write(buf,0,n);
                }
            }finally {
                out.close();
            }
        }finally {
            in.close();
        }
        return null;
    }



    /**
     * <h2> Java7引入的try with resources实现自动的资源关闭</h2>
     * @return
     * @throws IOException
     */
    private String newTryWithResources() throws IOException{
        //1. 单个资源的使用与关闭
 /*       try(BufferedReader br = new BufferedReader(new FileReader(""))){
                return br.readLine();
        }
*/
        //2.多个资源的使用与关闭
        try (FileInputStream in = new FileInputStream("");
             FileOutputStream out = new FileOutputStream("")
        ){
                byte[] buffer = new byte[1024];
                int n=0;
                while ((n=in.read(buffer))>=0){
                    out.write(buffer,0,n);
                }
        }
        return null;
    }


    public static void main(String[] args) throws MyException{

        /*
        AutoClose autoClose = new AutoClose();
        try {
            autoClose.work();
        }finally {
            autoClose.close();
        }*/

        try (AutoClose autoClose = new AutoClose()){
            autoClose.work();
        }



    }




}
