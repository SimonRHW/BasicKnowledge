package com.simon.java.rust;

public class HelloWorld {
    // a native library.
    private static native String hello(String input);

    static {
        //此处替换自己编译出来的.so路径即可
        System.load("/Users/home/Android/BasicKnowledge/src/main/java/com/simon/java/rust/lib/libBasicKnowledge.dylib");
    }

    public static void main(String[] args) {
        String output = HelloWorld.hello("josh");
        System.out.println(output);
    }
}
