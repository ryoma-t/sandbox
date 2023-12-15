package com.ryoma2pick.sandbox.dsa.leetcode.median_of_two_sorted_arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianOfTwoSortedArraysTest {

    @Test
    void median() throws IllegalArgumentException {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        assertEquals(2.5, medianOfTwoSortedArrays.findMedianSortedArrays(
                new int[]{1, 2},
                new int[]{3, 4}
        ));
        assertEquals(3, medianOfTwoSortedArrays.findMedianSortedArrays(
                new int[]{1},
                new int[]{2, 3, 4, 5}
        ));
        assertEquals(3, medianOfTwoSortedArrays.findMedianSortedArrays(
                new int[]{},
                new int[]{3}
        ));
        assertEquals(3, medianOfTwoSortedArrays.findMedianSortedArrays(
                new int[]{3},
                new int[]{1, 2, 4, 5}
        ));
        assertEquals(-1, medianOfTwoSortedArrays.findMedianSortedArrays(
                new int[]{3},
                new int[]{-2, -1}
        ));
        assertEquals(0, medianOfTwoSortedArrays.findMedianSortedArrays(
                new int[]{0, 0},
                new int[]{0, 0}
        ));
    }

    @Test
    void whenOneArrayIsEmptyItShouldReturnCorrectly() throws IllegalArgumentException {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        assertEquals(0, medianOfTwoSortedArrays.binarySearch(new int[]{}, new int[]{1}));
        assertEquals(1, medianOfTwoSortedArrays.binarySearch(new int[]{1}, new int[]{}));
        assertEquals(0, medianOfTwoSortedArrays.binarySearch(new int[]{}, new int[]{}));
    }

    @Test
    void whenNums1LeftIsLessThanNums2RightItReturnTrue() throws IllegalArgumentException {
        // setup
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        // execute
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int l1 = 1;
        int l2 = 1;
        boolean nums1LeftIsLessThanNums2Right = medianOfTwoSortedArrays.nums1LeftIsLessThanNums2Right(nums1, nums2, l1, l2);
        // verify
        assertTrue(nums1LeftIsLessThanNums2Right);
    }

    @Test
    void whenNums1LeftIsGreaterThanNums2RightItReturnFalse() throws IllegalArgumentException {
        // setup
        int[] nums1 = new int[]{3, 4};
        int[] nums2 = new int[]{1, 2};
        // execute
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int l1 = 1;
        int l2 = 1;
        boolean nums1LeftIsLessThanNums2Right = medianOfTwoSortedArrays.nums1LeftIsLessThanNums2Right(nums1, nums2, l1, l2);
        // verify
        assertFalse(nums1LeftIsLessThanNums2Right);
    }

    @Test
    void whenL1IsZeroItReturnFalse() throws IllegalArgumentException {
        // setup
        int[] nums1 = new int[]{3, 4};
        int[] nums2 = new int[]{1, 2};
        // execute
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int l1 = 0;
        int l2 = 2;
        boolean nums1LeftIsLessThanNums2Right = medianOfTwoSortedArrays.nums1LeftIsLessThanNums2Right(nums1, nums2, l1, l2);
        // verify
        assertTrue(nums1LeftIsLessThanNums2Right);
    }

    @Test
    void whenL1IsGreaterThanLengthItShouldThrowException() {
        // setup
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        // execute
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        int l1 = 3;
        int l2 = 1;
        try {
            medianOfTwoSortedArrays.nums1LeftIsLessThanNums2Right(nums1, nums2, l1, l2);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

}