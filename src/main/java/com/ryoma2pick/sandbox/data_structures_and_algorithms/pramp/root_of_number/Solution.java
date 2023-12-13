package com.ryoma2pick.sandbox.data_structures_and_algorithms.pramp.root_of_number;

public class Solution {

    static double root(double x, int n) {
        return binarySearch(0, x, x, n);
    }

    static double binarySearch(double start, double end, double x, int n) {
        double mid = (start + end) / 2;
        double acceptableErr = 0.001;
        if (Math.abs(x - Math.pow(mid, n)) < acceptableErr) return mid;
        if (x > Math.pow(mid, n)) return binarySearch(mid, end, x, n);
        return binarySearch(start, mid, x, n);
    }

    public static void main(String[] args) {
        // setup
        Solution solution = new Solution();
        // execute & verify
        /*
        input:  x = 9, n = 2
        output: 3
        */
        System.out.println("If x=9 and n=2, the expected result is 3. The actual value is "
                + solution.root(9, 2));
        /*
        input:  x = 7, n = 3
        output: 1.913
         */
        System.out.println("If x=7 and n=3, the expected result is 1.193. The actual value is "
                + solution.root(7, 3));

    }

}
