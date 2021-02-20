package com.simon.java.algorithm;

import java.util.Arrays;

public class MergingSort {
    /**
     * 归并排序（递归）
     *
     * ①. 将序列每相邻两个数字进行归并操作，形成 floor(n/2)个序列，排序后每个序列包含两个元素；
     * ②. 将上述序列再次归并，形成 floor(n/4)个序列，每个序列包含四个元素；
     * ③. 重复步骤②，直到所有元素排序完毕。
     * @param arr	 待排序数组
     */
    public static int[] mergingSort(int[] arr){
        if(arr.length <= 1) {
            return arr;
        }
        int num = arr.length >> 1;
        int[] leftArr = Arrays.copyOfRange(arr, 0, num);
        int[] rightArr = Arrays.copyOfRange(arr, num, arr.length);
        System.out.println("split two array: " + Arrays.toString(leftArr) + " And " + Arrays.toString(rightArr));
        //不断拆分为最小单元，再排序合并
        return mergeTwoArray(mergingSort(leftArr), mergingSort(rightArr));
    }

    private static int[] mergeTwoArray(int[] arr1, int[] arr2){
        int i = 0, j = 0, k = 0;
        //申请额外的空间存储合并之后的数组
        int[] result = new int[arr1.length + arr2.length];
        //选取两个序列中的较小值放入新数组
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] <= arr2[j]){
                result[k++] = arr1[i++];
            }else{
                result[k++] = arr2[j++];
            }
        }
        //序列1中多余的元素移入新数组
        while(i < arr1.length){
            result[k++] = arr1[i++];
        }
        //序列2中多余的元素移入新数组
        while(j < arr2.length){
            result[k++] = arr2[j++];
        }
        System.out.println("Merging: " + Arrays.toString(result));
        return result;
    }
}
