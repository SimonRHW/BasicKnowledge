package com.simon.java.functiontest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormatTest {

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
}
