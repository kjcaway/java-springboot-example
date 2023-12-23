package me.javaexample.javademo.util;

import java.text.SimpleDateFormat;

public class DateUtils {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getNowTimeStr() {
        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
