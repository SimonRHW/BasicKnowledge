package com.simon.java.functiontest;

import java.util.Date;

public class UtilsTest {

    public static void main(String[] args) {
        Date ss = new Date();
        System.out.println("一般日期输出：" + ss);
        System.out.println("时间戳：" + ss.getTime());
        System.out.println(DateUtils.isToday(ss.getTime()));
    }
}
