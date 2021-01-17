package com.hjy.spring.escape.http_request_response;

import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @auther: hjy
 * @Date: 2021/1/8 16:28
 * @Description:
 */

public class RequestParseUtil {

    public static boolean isJson(HttpServletRequest request){
        if (request.getContentType()!=null){
            return request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)
            ||request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE);
        }
        return false;
    }

    public static String getBodyString(final ServletRequest request){
            try {
                return inputStream2String(request.getInputStream());
            }catch (IOException ex){
                throw new RuntimeException();
            }
    }

    private static String inputStream2String(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
                reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
                String line;
                while ((line=reader.readLine())!=null){
                    sb.append(line);
                }

        }catch (IOException e){
            throw new RuntimeException();
        }
        return sb.toString();
    }

}
