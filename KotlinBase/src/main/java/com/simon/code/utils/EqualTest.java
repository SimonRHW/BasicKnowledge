package com.simon.code.utils;

public class EqualTest {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        String str3 = new String("abc");
        System.out.println(str1 == str3);
        System.out.println(str1.equals(str3));

    }
}
