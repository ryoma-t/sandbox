package org.algorithm;

public class MergeSort {

    public static int sort(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        return sortHelper(nums, start, end);
    }

    private static int sortHelper(int[] nums, int start, int end) {
        int inversionCount = 0;

        if (start >= end) return inversionCount;
        int mid = start + (end - start) / 2;

        inversionCount += sortHelper(nums, start, mid);
        inversionCount += sortHelper(nums, mid + 1, end);
        inversionCount += merge(nums, start, end, mid);
        return inversionCount;

    }

    private static int merge(int[] nums, int start, int end, int mid) {
        int inversionCount = 0;

        int i = start;
        int j = mid + 1;
        int count = 0;

        int[] tmp = new int[end - start + 1];
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                tmp[count] = nums[i];
                i++;
                count++;
            } else {
                tmp[count] = nums[j];
                j++;
                count++;
                inversionCount += mid - i+1;
                continue;
            }
        }

        while (i <= mid) {
            tmp[count] = nums[i];
            i++;
            count++;
        }
        while (j <= end) {
            tmp[count] = nums[j];
            j++;
            count++;
        }

        int k = 0;
        while (k < tmp.length) {
            nums[start + k] = tmp[k];
            k++;
        }
        return inversionCount;
    }

}
