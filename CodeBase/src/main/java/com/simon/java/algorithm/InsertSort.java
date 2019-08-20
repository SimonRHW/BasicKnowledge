package com.simon.java.algorithm;

import java.util.Arrays;

/**
 * @author Simon
 */
public class InsertSort {

    public static void insertionSort(int[] arr){
        for( int i = 1; i < arr.length; i++ ) {
            int temp = arr[i];
            for( int j = i; j >= 0; j-- ) {
                if( j > 0 && arr[j-1] > temp ) {
                    arr[j] = arr[j-1];
                    System.out.println("Temping:  " + Arrays.toString(arr));
                } else {
                    arr[j] = temp;
                    break;
                }
            }
        }
    }

    // 交换次数较多的实现
    public static void insertionSwapSort(int[] arr){
        for( int i=0; i<arr.length-1; i++ ) {
            for( int j=i+1; j>0; j-- ) {
                if( arr[j-1] <= arr[j] )
                    break;
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }

}
