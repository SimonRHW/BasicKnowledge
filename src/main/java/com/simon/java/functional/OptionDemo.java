package com.simon.java.functional;

import java.util.Optional;

/**
 * @author Simon
 */
public class OptionDemo {

    public static void main(String[] Args) {
        System.out.println(divideFunctional(4,2));
        System.out.println(divideFunctional(4,0));
    }

    public static Optional<Double> divideFunctional(int x, int y) {
        if (y == 0) {
            return Optional.empty();
        } else {
            return Optional.of((double) x / y);
        }
    }
}
