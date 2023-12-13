package org.pramp.absolute_value_sort;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] arr1 = new int[]{2, -7, -2, -2, 0};
        sol.sort(arr1);
        System.out.println("expected is [0, -2, -2, 2, -7]. actual is ");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }

        int[] arr2 = new int[]{2, -7, -2, -2, 0};
        sol.sort2(arr2);
        System.out.println("expected is [0, -2, -2, 2, -7]. actual is ");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr1[i] + " ");
        }
    }

    private void sort2(int[] arr) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) != Math.abs(o2)) {
                    if (Math.abs(o1) < Math.abs(o2)) return -1;
                    return 1;
                }
                if (o1 < o2) return -1;
                return 1;
            }
        };

        Integer[] arrInteger = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrInteger[i] = arr[i];
        }

        Arrays.sort(arrInteger, comparator);
        for(int i=0;i<arrInteger.length;i++){
            arr[i]=arrInteger[i];
        }

    }

    private void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (compare(arr[j], min)) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    private static boolean compare(int i1, int i2) {
        if (Math.abs(i1) != Math.abs(i2)) {
            return Math.abs(i1) <= Math.abs(i2);
        }
        return i1 <= i2;
    }

}
