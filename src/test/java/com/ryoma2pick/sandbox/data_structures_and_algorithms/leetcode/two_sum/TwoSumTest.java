package com.ryoma2pick.sandbox.data_structures_and_algorithms.leetcode.two_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoSumTest {

    @Test
    void twoSum() {
        // setup
        int[] array = new int[]{2, 7, 11, 15};
        int target = 9;
        // execute
        int[] output = TwoSum.twoSum(array, target);
        // verify
        assertEquals(2, output.length);
        assertEquals(target, array[0] + array[1]);
    }

    @Test
    void twoSum_identical_complement() {
        // setup
        int[] array = new int[]{3, 3};
        int target = 6;
        // execute
        int[] output = TwoSum.twoSum(array, target);
        // verify
        assertEquals(2, output.length);
        assertEquals(target, array[0] + array[1]);
    }

}