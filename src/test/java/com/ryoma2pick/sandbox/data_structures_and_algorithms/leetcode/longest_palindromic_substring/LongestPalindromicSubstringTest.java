package org.leetcode.longest_palindromic_substring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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