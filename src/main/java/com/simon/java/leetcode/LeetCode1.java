package com.simon.java.leetcode;

import java.util.HashMap;

public class LeetCode1 {

    public static void main(String[] args) {
        int[] nums = {2, 5, 5, 9, 10};
        int[] sum = sum(nums, 10);
        for (int item : sum) {
            System.out.println(item);
        }
    }

    public static int[] sum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            int temp = target - num;
            if (hashMap.containsKey(temp)) {
                res[0] = i;
                res[1] = hashMap.get(temp);
            } else {
                hashMap.put(nums[i], i);
            }
        }
        return res;
    }

}
