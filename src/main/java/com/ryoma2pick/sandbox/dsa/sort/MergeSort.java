package com.ryoma2pick.sandbox.dsa.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 6, 1, 2};
        MergeSort sol = new MergeSort();

        sol.mergeSort(arr);
        Arrays.stream(arr).forEach(System.out::println);

        int count = sol.mergeSortInversionCount(arr);
        System.out.println(count);
    }

    public void mergeSort(int[] arr) {
        merge(0, arr.length - 1, arr);
    }

    public int mergeSortInversionCount(int[] arr) {
        return mergeInversionCount(0, arr.length - 1, arr);
    }

    public void merge(int start, int end, int[] arr) {
        if (start == end) return;

        int mid = start + (end - start) / 2;
        merge(start, mid, arr);
        merge(mid + 1, end, arr);

        int[] sorted = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        while (i <= mid || j <= end) {
            int l = (i <= mid) ? arr[i] : Integer.MAX_VALUE;
            int r = (j <= end) ? arr[j] : Integer.MAX_VALUE;
            if (l <= r) {
                sorted[k] = arr[i];
                i++;
            } else {
                sorted[k] = arr[j];
                j++;
            }
            k++;
        }
        for (int l = 0; l < (end - start + 1); l++) {
            arr[start + l] = sorted[l];
        }
    }

    public int mergeInversionCount(int start, int end, int[] arr) {
        if (start == end) return 0;

        int mid = start + (end - start) / 2;
        int leftInverseCount = mergeInversionCount(start, mid, arr);
        int rightInverseCount = mergeInversionCount(mid + 1, end, arr);

        int[] sorted = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;

        int currentInverseCount = 0;
        while (i <= mid || j <= end) {
            int l = (i <= mid) ? arr[i] : Integer.MAX_VALUE;
            int r = (j <= end) ? arr[j] : Integer.MAX_VALUE;
            if (l <= r) {
                sorted[k] = arr[i];
                i++;
            } else {
                currentInverseCount += (mid - i + 1);
                sorted[k] = arr[j];
                j++;
            }
            k++;
        }
        for (int l = 0; l < (end - start + 1); l++) {
            arr[start + l] = sorted[l];
        }

        return leftInverseCount + rightInverseCount + currentInverseCount;
    }
    // [4 6 1 2]
    //  0 1 2 3
    /**
     * invoke the function helper(start=0,end=arr.length-1)
     *  if start == end, return
     *
     *  mid = (start + end) / 2
     *  helper(start, mid)
     *  helper(mid + 1, end)
     *
     *  declare
     *      sorted; (end - start + 1)-sized array
     *      k = 0
     *  iterate through as long as i <= mid OR j <= end
     *          left subarray with index i = start, 1, ..., mid
     *          right subarray wit index j = mid + 1, ..., end
     *      l = (i <= mid) ? arr[i] : Integer.MAX_VALUE
     *      r = (j <= end) ? arr[j] : Integer.MAX_VALUE
     *      if l <= r,
     *          sorted[k] = arr[i]
     *          i++
     *      else,
     *          sorted[k] = arr[j]
     *          j++
     *      k++
     *  overwrite arr[start, end] with sorted
     * ---
     * tc: O(n*log(n))
     * sc: O(n)
     */
}