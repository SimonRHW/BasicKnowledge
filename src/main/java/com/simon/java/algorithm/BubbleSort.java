package com.simon.java.algorithm;

import java.util.Arrays;

/**
 * @author Simon
 */
public class BubbleSort {

    private static int[] sort = new int[]{10, 20, 3, 5, 6, 4, 9, 8, 12, 1, 17, 34, 11};

    public static void main(String[] args) {
        bubbleSort(sort);
        bubblesort(sort);
    }

    public static int[] bubbleSort(int[] data) {
        int temp;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
        return data;
    }

    public static String bubblesort(int[] arr) {
        int tmp;
        for (int a = 0; a < arr.length - 1; a++) {
            boolean flag = false;
            for (int b = 0; b < arr.length - a - 1; b++) {
                if (arr[b + 1] < arr[b]) {
                    tmp = arr[b];
                    arr[b] = arr[b + 1];
                    arr[b + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) break;
        }
        return Arrays.toString(arr);
    }

}
