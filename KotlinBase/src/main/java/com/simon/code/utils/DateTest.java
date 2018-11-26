package com.simon.code.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
//        Date ss = new Date();
//        System.out.println("一般日期输出：" + ss);
//        System.out.println("时间戳：" + ss.getTime());
//        //Date aw = Calendar.getInstance().getTime();//获得时间的另一种方式，测试效果一样
//        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format0.format(ss.getTime());//这个就是把时间戳经过处理得到期望格式的时间
//        System.out.println("格式化结果0：" + time);
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//        time = format1.format(ss.getTime());
//        System.out.println("格式化结果1：" + time);
//        System.out.println("格式化结果2：" + lastWeek(14));


        System.out.println(toDateString("2018-10-17 11:31:57 0"));
    }


    public static String toDateString(String dateStr) {
        Date date = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        try {
            date = sdf.parse(dateStr);
            return toDateString(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.toString();
    }

    public static String toDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
        return myFmt.format(dt);
    }














    public static String toLongDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        return myFmt.format(dt);
    }

    public static String toShortDateString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yy年MM月dd日 HH时mm分");
        return myFmt.format(dt);
    }






    public static String toLongTimeString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("HH mm ss SSSS");
        return myFmt.format(dt);
    }

    public static String toShortTimeString(Date dt) {
        SimpleDateFormat myFmt = new SimpleDateFormat("yy/MM/dd HH:mm");
        return myFmt.format(dt);
    }

    private static String lastWeek(int days) {
        Date date = new Date();
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(date)) - days;

        if (day < 1) {
            month -= 1;
            if (month == 0) {
                year -= 1;
                month = 12;
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                day = 30 + day;
            } else if (month == 1 || month == 3 || month == 5 || month == 7
                    || month == 8 || month == 10 || month == 12) {
                day = 31 + day;
            } else if (month == 2) {
                if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
                    day = 29 + day;
                else
                    day = 28 + day;
            }
        }
        String y = year + "";
        String m = "";
        String d = "";
        if (month < 10)
            m = "0" + month;
        else
            m = month + "";
        if (day < 10)
            d = "0" + day;
        else
            d = day + "";
        return y + "-" + m + "-" + d + " 00:00:00";
    }
}
