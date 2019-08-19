package com.simon.java.algorithm;


public class Recursive {

    public static void main(String[] args) {

        System.out.println(Recursive.doRecursive(0));
        System.out.println(Recursive.doRecursive(1));
        System.out.println(Recursive.doRecursive(2));
        System.out.println(Recursive.doRecursive(3));

    }


    public static int doRecursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * doRecursive(n - 1);
    }

}
