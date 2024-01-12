package com.simon.java.rust;

public class HelloWorld {
    // a native library.
    private static native String hello(String input);

    static {
        //System.load参数必须为库文件的绝对路径，可以是任意路径，此处替换自己编译出来的.so路径即可
        System.load("/Users/rhw/CodeSource/ownerProject/BasicKnowledge/src/main/java/com/simon/java/rust/lib/libBasicKnowledge.dylib");
    }

    public static void main(String[] args) {
        String output = HelloWorld.hello("rust");
        System.out.println(output);
    }
}
