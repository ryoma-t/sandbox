package com.ryoma2pick.sandbox.data_structures_and_algorithms.sort;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void name() {
        int[] nums = new Random().ints(5, 0, 10).toArray();
        Arrays.stream(nums).forEach(System.out::println);

        int actual = MergeSort.sort(nums);

        int expected = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) expected++;
            }
        }
        assertEquals(expected, actual);
    }

}