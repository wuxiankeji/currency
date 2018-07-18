package com.mlink.base.common.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils {


    // 是否为空
    public static  boolean isEmpty(String value) {

        if(value == null)   return true;
        if(value.equals(""))   return true;
        return false;
    }

    public static  String getCurTime() {
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return  df.format(day);
    }

    public static  String getCurDate() {
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        return  df.format(day);
    }



    public static  Date strToDate(String strDate) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;


    }

    public static  String getUUID() {
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }

    // HttpServletResponse里装载image文件流
    public static void responseLoadImage(HttpServletResponse response, String imagePath) {
        // 根据文件扩展名，形成content-type
        String contentType = "application/octet-stream";
        if (imagePath.toLowerCase().endsWith("png")) {
            contentType = "image/png";
        } else if (imagePath.toLowerCase().endsWith("jpg")) {
            contentType = "image/jpeg";
        }

        response.setHeader("Content-Type", contentType);
        // 读取图像文件,返回浏览器
        try {

            FileInputStream in = new FileInputStream(imagePath);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                out.write(buffer);
            }
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
