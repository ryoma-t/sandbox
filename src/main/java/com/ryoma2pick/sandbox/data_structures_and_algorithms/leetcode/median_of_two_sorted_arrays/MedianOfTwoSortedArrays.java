package com.ryoma2pick.sandbox.data_structures_and_algorithms.leetcode.median_of_two_sorted_arrays;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l = (nums1.length + nums2.length + 1) / 2;
        int r = (nums1.length + nums2.length) - l;

        int l1 = binarySearch(nums1, nums2);
        int l2 = l - l1;

        int maxL1 = (nums1.length > 0 && l1 > 0) ? nums1[l1 - 1] : Integer.MIN_VALUE;
        int maxL2 = (nums2.length > 0 && l2 > 0) ? nums2[l2 - 1] : Integer.MIN_VALUE;
        int maxL = Math.max(maxL1, maxL2);

        if (l != r) {
            return maxL;
        }

        int minR1 = (nums1.length > 0 && l1 < nums1.length) ? nums1[l1] : Integer.MAX_VALUE;
        int minR2 = (nums2.length > 0 && l2 < nums2.length) ? nums2[l2] : Integer.MAX_VALUE;
        int minR = Math.min(minR1, minR2);

        return (double) (maxL + minR) / 2;
    }

    int binarySearch(int[] nums1, int[] nums2) {
        int leftMergedLength = (nums1.length + nums2.length + 1) / 2;
        if (nums1.length == 0) {
            return 0;
        }
        if (nums2.length == 0) {
            return leftMergedLength;
        }
        return binarySearch(nums1, nums2, 0, leftMergedLength, leftMergedLength);
    }

    boolean nums1LeftIsLessThanNums2Right(int[] nums1, int[] nums2, int l1, int l2) throws IllegalArgumentException {
        if (l1 > nums1.length || l2 > nums2.length) {
            throw new IllegalArgumentException();
        }

        if (l1 == 0) {
            return true;
        }
        if (l2 == nums2.length) {
            return true;
        }
        return nums1[l1 - 1] <= nums2[l2];
    }

    private int binarySearch(int[] nums1, int[] nums2, int start, int end, int leftMergedLength) {
        int l1 = (start + end + 1) / 2;
        int l2 = leftMergedLength - l1;

        if (l1 > nums1.length) {
            return binarySearch(nums1, nums2, start, l1 - 1, leftMergedLength);
        }
        if (l2 > nums2.length) {
            return binarySearch(nums1, nums2, l1, end, leftMergedLength);
        }

        if (!nums1LeftIsLessThanNums2Right(nums1, nums2, l1, l2)) {
            l1 = binarySearch(nums1, nums2, start, l1 - 1, leftMergedLength);
        }
        if (!nums1LeftIsLessThanNums2Right(nums2, nums1, l2, l1)) {
            l1 = binarySearch(nums1, nums2, l1, end, leftMergedLength);
        }
        return l1;
    }

}
