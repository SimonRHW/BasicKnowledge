package com.simon.java.utils;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    public static void main(String[] args) {

        String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(speChat);
        Matcher matcher = pattern.matcher("hhjk   .");
        System.out.println(matcher.find());

        String returnValue = null;
        try {
            returnValue = new String(Base64.decode("JA1BtfFuS8eLz2/wQy/8wudOCNGwLpjmvCOXreOlV4/gWLslTUcGPVbn8px4gCtffK2FSQvqHqIrPFaixpgka2+Hb8DrA93A7DlAUaEkx2oOA5AgNTdFKZRqM6ocQSnMjYMbY0mJDH0Mm/1jU5T55uTHDvwmRQ12jQLodwIQcao="));
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }
        System.out.println(returnValue);
    }

    public static void checkMac() {
        String mac = "D3:41:89:A7:05:C8";
        String[] split = mac.split(":");
        String majorHEX = "41" + split[3];
        String minorHEX = split[4] + split[5];
        System.out.println(mac + "====majorHEX====" + majorHEX);
        System.out.println(mac + "====minorHEX====" + minorHEX);
        Integer major = Integer.parseInt(majorHEX, 16);
        Integer minor = Integer.parseInt(minorHEX, 16);
        System.out.println(mac + "====major====" + major);
        System.out.println(mac + "====minor====" + minor);

    }
}
