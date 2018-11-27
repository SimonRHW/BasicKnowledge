package com.simon.java.annotation;

public class NoBug {
    @TestAnnotation
    public void number() {
        System.out.println("1234567890");
    }

    @TestAnnotation
    public void jiafa() {
        System.out.println("1+1=" + 1 + 1);
    }

    @TestAnnotation
    public void jianfa() {
        System.out.println("1-1=" + (1 - 1));
    }

    @TestAnnotation
    public void chengfa() {
        System.out.println("3 x 5=" + 3 * 5);
    }

    @TestAnnotation
    public void chuO() {
        System.out.println("6 / 0=" + 6 / 0);
    }

    @TestAnnotation
    public void noBug() {
        System.out.println("我写的程序没有 bug!");
    }
}
