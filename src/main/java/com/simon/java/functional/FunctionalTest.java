package com.simon.java.functional;

import java.util.HashMap;
import java.util.Map;

public class FunctionalTest {

    public static void main(String[] Args) {
        Map<String, Object> result = divide(4, 2);
        System.out.println((double) result.get("answer"));
        Map<String, Object> result1 = divide(4, 0);
        System.out.println(result1.get("exception"));
        System.out.println(divideFunctional(4,2).getRight());
        System.out.println(divideFunctional(4,0).getLeft());
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

    public static Either<Exception, Double> divideFunctional(int x, int y) {
        if (y == 0) {
            return Either.left(new Exception("div by zero"));
        } else {
            return Either.right(((double) x / y));
        }
    }
}
