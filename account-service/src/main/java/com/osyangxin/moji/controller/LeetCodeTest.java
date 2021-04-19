package com.osyangxin.moji.controller;

import java.util.Arrays;

/**
 * @author yangxin
 * @类描述
 * @time 2021/3/21  16:42
 */
public class LeetCodeTest {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(exchange(new int[]{1, 11, 14})));
    }

    public static int[] exchange(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            while (start < end && start < nums.length - 1 && nums[start] % 2 == 1) {
                start++;
            }
            while (start < end && end > 0 && nums[end] % 2 == 0) {
                end--;
            }
            int swap = nums[start];
            nums[start] = nums[end];
            nums[end] = swap;
            start++;
            end--;
        }

        return nums;

    }

}
