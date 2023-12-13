package com.ryoma2pick.sandbox.data_structures_and_algorithms.leetcode.two_sum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer complementIndex = map.get(complement);
            if (complementIndex == null) {
                map.put(nums[i], i);
                continue;
            }
            result[0] = i;
            result[1] = map.get(complement);
        }
        return result;
    }

}
