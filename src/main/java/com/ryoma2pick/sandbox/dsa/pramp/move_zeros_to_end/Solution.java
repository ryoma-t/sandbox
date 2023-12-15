package com.ryoma2pick.sandbox.dsa.pramp.move_zeros_to_end;

public class Solution {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 10, 0, 2, 8, 3, 0, 0, 6, 4, 0, 5, 7, 0};
        int[] expected = new int[]{1, 10, 2, 8, 3, 6, 4, 5, 7, 0, 0, 0, 0, 0};
        int[] actual = moveZerosToEnd(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.format("expected: %s, actual: %s%n", expected[i], actual[i]);
        }
    }

    static int[] moveZerosToEnd(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) continue;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] == 0) continue;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                break;
            }
        }
        return arr;
    }

}
