package com.simon.java.algorithm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Simon
 * Function:
 */
public class SortTest {

    private static final int DATA_LENGTH = 10;

    public static void main(String[] args) {

        int[] data = new int[DATA_LENGTH];
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            for (int i = 0; i < DATA_LENGTH; i++) {
                data[i] = random.nextInt();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        System.out.println("onStart ==" + start);
//        InsertSort.insertionSort(data);
//        InsertSort.insertionSwapSort(data);
//        ShellSort.shell_sort(data);
//        BubbleSort.bubbleSort(data);
        SelectionSort.selectionSort(data);
        long end = System.currentTimeMillis();
        System.out.println("onComplete ==" + end);
        System.out.println("time ==" + (end - start));
        for (int datum : data) {
            System.out.println(datum);
        }
        System.out.println("sort time ==" + (end - start));
    }
}
