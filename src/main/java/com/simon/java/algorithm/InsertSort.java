package com.simon.java.algorithm;

/**
 * @author Simon
 * 直接插入排序
 * 算法实现中比较有意思的一点是，在每次比较操作发现取出来的新元素小于等于已排序的元素时，
 * 可以将已排序的元素移到下一位置，然后将取出来的新元素插入该位置（即相邻位置对调），
 * 接着再与前面的已排序的元素进行比较，这样做缺点是交换操作代价比较大。
 * 另一种做法是：将新元素取出（挖坑），从左到右依次与已排序的元素比较，如果已排序的元素大于取出的新元素，
 * 那么将该元素移动到下一个位置（填坑），接着再与前面的已排序的元素比较，
 * 直到找到已排序的元素小于等于新元素的位置，这时再将新元素插入进去
 */
public class InsertSort {

    public static void insertionSort(int[] arr){
        for( int i = 1; i < arr.length; i++ ) {
            int temp = arr[i];
            for( int j = i; j >= 0; j-- ) {
                if( j > 0 && arr[j-1] > temp ) {
                    arr[j] = arr[j-1];
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
