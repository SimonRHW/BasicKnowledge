package com.simon.java.algorithm;


public class Recursive {

    /**
     * 递归实现n的阶乘
     */
    public static int doRecursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * doRecursive(n - 1);
    }

}
