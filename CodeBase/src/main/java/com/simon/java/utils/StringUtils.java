package com.simon.java.utils;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class StringUtils {

    public static void  main(String args[]){
        String test1 = "eyJhbGx3aW5kb3dzb3BlbiI6IiJ9";

        String test2 = "{\"RestrictPowerOff\":\"failed\"}";

        String commandValue = new String(Base64.decode(test1));
        System.out.println(commandValue);

    }

}
