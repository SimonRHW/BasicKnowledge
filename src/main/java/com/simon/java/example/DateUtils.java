package com.simon.java.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static void main(String args[]) {
        System.out.println(isValidDate("2019-07-15 16:00:00"));
        System.out.println(System.currentTimeMillis());
    }

    private static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        //2019-05-29 03:00:05
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            format.setLenient(true);
            format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static boolean isToday(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //参数时间
        String param = sdf.format(date);
        //当前时间
        String now = sdf.format(new Date());
        return param.equals(now);
    }


    public static String formatTime(int totalTime) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = totalTime / 1000;
        if (totalTime <= 1000 && totalTime > 0) {
            second = 1;
        }
        if (second > 60) {
            minute = second / 60;
            second = second % 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        // 转换时分秒 00:00:00
        return (hour >= 10 ? hour : "0" + hour) + ":" + (minute >= 10 ? minute : "0" + minute) + ":" + (second >= 10 ? second : "0" + second);
    }

    public static void testDate() {
        Date ss = new Date();
        System.out.println("一般日期输出：" + ss);
        System.out.println("时间戳：" + ss.getTime());
        System.out.println(DateUtils.isToday(ss.getTime()));
    }
}
