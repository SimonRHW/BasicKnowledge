package com.simon.java.utils;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class StringUtils {

    public static void  main(String args[]){
        String test1 = "eyJhbGx3aW5kb3dzb3BlbiI6IiJ9";

        String test2 = "{\"RestrictPowerOff\":\"failed\"}";

        try {
            String commandValue = new String(Base64.decode(test1));
            System.out.println(commandValue);
        } catch (Base64DecodingException e) {
            e.printStackTrace();
        }

    }

}
