package org.leetcode.zigzag_conversion;

class ZigzagConversion {

    public String convert(String s, int numRows) {

        if (s.length() <= 2) return s;
        if (numRows <= 1) return s;

        char[][] arr = new char[numRows][s.length()];
        int bridgeLength = numRows - 2;

        int count = 0;
        int j = 0;
        while (count < s.length()) {
            for (int i = 0; i < numRows; i++) {
                arr[i][j] = s.charAt(count);
                count++;
                if (count >= s.length()) break;
            }

            j++;
            if (count >= s.length()) break;
            for (int i = 1; i <= bridgeLength; i++) {
                arr[(numRows - 1) - i][j] = s.charAt(count);
                count++;
                j++;
                if (count >= s.length()) break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < arr.length; n++) {
            for (int m = 0; m < arr[0].length; m++) {
                if (arr[n][m] != '\u0000') {
                    sb.append(arr[n][m]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}