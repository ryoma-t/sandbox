package com.ryoma2pick.sandbox.data_structures_and_algorithms.leetcode.longest_palindromic_substring;

import com.ryoma2pick.sandbox.data_structures_and_algorithms.leetcode.longest_palindromic_substring.LongestPalindromicSubstring;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestPalindromicSubstringTest {

    @Test
    void name() {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        assertEquals(
                "bab", longestPalindromicSubstring.longestPalindrome(
                        "babad"
                ));
        assertEquals(
                "bb", longestPalindromicSubstring.longestPalindrome(
                        "cbbd"
                ));
        assertEquals(
                "", longestPalindromicSubstring.longestPalindrome(
                        ""
                ));
        assertEquals(
                "a", longestPalindromicSubstring.longestPalindrome(
                        "a"
                ));
        assertEquals(
                "aaa", longestPalindromicSubstring.longestPalindrome(
                        "aaa"
                ));
    }

}