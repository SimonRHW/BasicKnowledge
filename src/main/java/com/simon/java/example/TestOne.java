package com.simon.java.example;

public class TestOne {
    private static final int i = 75;

    public static void main(String[] args) {
        testMiSra();
        int i =Math.round(1);
        String java = "I am Java";
    }

    public static boolean add(int i) {
        i += 1;
        return 3> i;
    }

    public static void testMiSra() {
        int y = 1;
        int i = 0;
        for (int x = 0; x < y; x = y++) {
            i++;
        }

    }

    public static void test1() {
        int num = 2147483647;
        num += 2;
        System.out.println(num);
    }

    public static void test2() {
        String str = "";
        for (int i = 0; i < 5; i++) {
            str += i;
        }
        System.out.println(str);
    }

    public static void test3() {
        int num = 50;
        num = num++ * 2;
        System.out.println(num);
    }

    public static void test4() {
        int num = 2147483647;
        num += 2L;
        System.out.println(num);
    }

//    public  int test5() {
//        static int i = 0;
//        i++;
//        return i;
//    }

    public static void test6() {
        int i = 9;
        switch (i) {
            default:
                System.out.println("default");
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
            case 2:
                System.out.println("two");
        }

    }


}
