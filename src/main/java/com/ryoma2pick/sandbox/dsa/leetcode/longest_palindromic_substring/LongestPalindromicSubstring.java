package com.ryoma2pick.sandbox.dsa.leetcode.longest_palindromic_substring;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }

        String palindrome = s.substring(0, 1);
        String longestParindrome = palindrome;

        char[] sArray = s.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            longestParindrome = expandPalindrome(s, longestParindrome, sArray, i - 1, i + 1);

            if (i >= sArray.length - 1) {
                continue;
            }

            if (sArray[i] == sArray[i + 1]) {
                longestParindrome = updateLongest(s, longestParindrome, i, i + 2);
                longestParindrome = expandPalindrome(s, longestParindrome, sArray, i - 1, i + 2);
            }
        }

        return longestParindrome;
    }

    private String expandPalindrome(String s, String longestParindrome, char[] sArray, int l, int r) {
        if (outOfIndex(sArray, l, r)) {
            return longestParindrome;
        }
        while (sArray[l] == sArray[r]) {
            longestParindrome = updateLongest(s, longestParindrome, l, r + 1);
            l--;
            r++;
            if (outOfIndex(sArray, l, r)) {
                break;
            }
        }
        return longestParindrome;
    }

    private boolean outOfIndex(char[] sArray, int l, int r) {
        return l < 0 || r >= sArray.length;
    }

    private String updateLongest(String s, String longest, int start, int end) {
        String palindrome;
        palindrome = s.substring(start, end);
        return (palindrome.length() > longest.length())
                ? palindrome : longest;
    }

}
