package com.simon.java.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.reactivex.Observable;

public class StringTest {
    public static void main(String[] args) {

        String returnValue = new String(Base64.decode("eyJkb29ydW5sb2NrIjoiZmFpbGVkIn0="));
        System.out.println(returnValue);

        String mac = "F3:88:A9:7A:BE:61";
        String str = mac.replace(":", "");
        System.out.println(mac + "========" + str);
        Observable<String> helloWorld = Observable.just("hello world");
    }
}
