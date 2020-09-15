package com.simon.java.functional;

import java.util.HashMap;
import java.util.Map;

public class FunctionalTest {

    public static void main(String[] Args) {
        Map<String, Object> result = divide(4, 2);
        System.out.println((double) result.get("answer"));
        Map<String, Object> result1 = divide(4, 0);
        System.out.println(result1.get("exception"));
    }

    public static Map<String, Object> divide(int x, int y) {

        Map<String, Object> result = new HashMap<>();
        if (y == 0) {
            result.put("exception", new Exception("div by zero"));
        } else {
            result.put("answer", (double) x / y);
        }
        return result;
    }
}
