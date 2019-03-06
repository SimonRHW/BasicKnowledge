package com.simon.java.utils;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by renhongwei on 2018/5/5.
 */
public class MainTest {


    public static void main(String[] args) {

        //121.485914,31.233032 cent 大世界
        //121.479231,31.238898   人民广场
        //121.493999,31.234066 豫园
        //double angle = getAngle(31.233032, 121.485914, 31.234066, 121.493999);
        //System.out.println(angle1);
        //System.out.println(date2String(1528095996000L,"yyyy年MM月dd日"));
        //System.out.println(isSixNumber("000000"));
        //System.out.println("".length()==0);
//        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        String tsStr = "2018-05-09 11:49:45";
//        try {
//            ts = Timestamp.valueOf(tsStr);
//            System.out.println(ts.getTime());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ArrayList list = new ArrayList();
//        System.out.println(chineseToNumber("2元"));


        int low = 10;
        int totle = 7794;
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)low/(float)totle);

        System.out.println(result);

    }

    /*
     * 中文转阿拉伯数字
     */
    public static double chineseToNumber(String chineseNum) {
        double number = 0;
        try {
            if (chineseNum.contains("元")) {
                chineseNum = chineseNum.replace("元", ".");
                System.out.println("0000========" + chineseNum);
                if (chineseNum.contains("角")) {
                    chineseNum = chineseNum.replace("角", "");
                    System.out.println("1111========" + chineseNum);
                } else {
                    if (chineseNum.contains("分")) {
                        String c = String.valueOf(chineseNum.charAt(chineseNum.indexOf("分") - 1));
                        chineseNum = chineseNum.replace(c, String.format("0%s", c));
                        System.out.println("1111========" + chineseNum);
                        chineseNum = chineseNum.replace("分", "");
                        System.out.println("2222========" + chineseNum);
                    }
                }

                if (chineseNum.contains("分")) {
                    chineseNum = chineseNum.replace("分", "");
                    System.out.println("2222========" + chineseNum);
                }

            } else {
                if (chineseNum.contains("角")) {
                    chineseNum = "0." + chineseNum;
                    chineseNum = chineseNum.replace("角", "");
                    System.out.println("333========" + chineseNum);
                    if (chineseNum.contains("分")) {
                        chineseNum = chineseNum.replace("分", "");
                        System.out.println("4444========" + chineseNum);
                    }
                } else {
                    if (chineseNum.contains("分")) {
                        chineseNum = chineseNum.replace("分", "");
                        chineseNum = "0.0" + chineseNum;
                        System.out.println("555========" + chineseNum);
                    }
                }
            }
            DecimalFormat df = new DecimalFormat("0.00");
            System.out.println("555========1" + chineseNum);
            chineseNum = df.format(Double.valueOf(chineseNum));
            System.out.println("555========2" + chineseNum);
            double res = Double.valueOf(chineseNum);
            System.out.println("555========3" + res);
            return res;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return number;
    }


    /**
     * @param lat_a 纬度1
     * @param lng_a 经度1
     * @param lat_b 纬度2
     * @param lng_b 经度2
     * @return
     */
    public static double getAngle(double lat_a, double lng_a, double lat_b, double lng_b) {
        double y = Math.sin(lng_b - lng_a) * Math.cos(lat_b);
        double x = Math.cos(lat_a) * Math.sin(lat_b) - Math.sin(lat_a) * Math.cos(lat_b) * Math.cos(lng_b - lng_a);
        double brng = Math.atan2(y, x);
        brng = Math.toDegrees(brng);
        if (brng < 0)
            brng = brng + 360;
        return brng;
    }

    /**
     * long 去除 时分秒
     * 时分秒全部为0
     *
     * @param date
     * @return
     */
    public static long getYearMonthDay(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * Date（long） 转换 String
     *
     * @param time
     * @param format
     * @return
     */
    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(time);
        return s;
    }


    /**
     * 判断字符串是否为六位纯数字
     *
     * @param str 字符串
     * @return 是否纯数字
     */
    public static boolean isSixNumber(String str) {
        String regExp = "^\\d{6}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 将毫秒时间转换成时分秒
     *
     * @param totalTime
     * @return
     */
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
        String duration = (hour >= 10 ? hour : "0" + hour) + ":" + (minute >= 10 ? minute : "0" + minute) + ":" + (second >= 10 ? second : "0" + second);
        return duration;
    }
}
