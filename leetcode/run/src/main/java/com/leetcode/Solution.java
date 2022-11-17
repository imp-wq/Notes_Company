package com.leetcode;

import org.junit.Test;

import java.sql.Array;
import java.util.Arrays;

public class Solution {
    @Test
    public void run() {
        int[] res = this.twoSum(new int[]{3, 2, 4}, 6);
        System.out.println(Arrays.toString(res));
    }

    // public int[] twoSum(int[] nums, int target) {
    //     for (int i = 1; i < nums.length; i++) {
    //         int j = 0;
    //         while (j < i) {
    //             int sum;
    //             if ((sum = nums[i] + nums[j]) == target) {
    //                 return new int[]{i, j};
    //             }
    //             j++;
    //         }
    //     }
    //     return new int[]{};
    // }

    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int sum;

            if ((sum = numbers[start] + numbers[end]) == target) {
                return new int[]{start + 1, end + 1};
            }
            if (sum < target) {
                int i = start++;
            } else {
                end--;
            }
        }
        return new int[0];
    }
}
