package com.simon.java.rust2j;


/**
 * @author Simon
 */
public class HelloRust {

    static {
        System.loadLibrary("src/main/resources/x86_64-apple-darwin/BasicKnowledge");
    }
    private static native int add(int left,int right);

    public static void main(String[] args) {
        int add = HelloRust.add(1, 2);
        System.out.println("add = " +add);
    }

}
