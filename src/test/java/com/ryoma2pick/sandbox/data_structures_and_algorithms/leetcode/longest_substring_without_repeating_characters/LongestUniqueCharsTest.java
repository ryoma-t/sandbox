package org.leetcode.longest_substring_without_repeating_characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestUniqueCharsTest {

    @Test
    void abcabcbbShouldReturnThree() {
        // setup
        String s = "abcabcbb";
        // execute
        LongestUniqueChars longestUniqueChars = new LongestUniqueChars();
        int length = longestUniqueChars.lengthOfLongestSubstring(s);
        // verify
        assertEquals(3, length);
    }

    @Test
    void stringComposedOfOneUniqueCharacterShouldReturnOne() {
        // setup
        String s = "bbbbb";
        // execute
        LongestUniqueChars longestUniqueChars = new LongestUniqueChars();
        int length = longestUniqueChars.lengthOfLongestSubstring(s);
        // verify
        assertEquals(1, length);
    }
    @Test
    void pwwkewShouldReturnThree() {
        // setup
        String s = "pwwkew";
        // execute
        LongestUniqueChars longestUniqueChars = new LongestUniqueChars();
        int length = longestUniqueChars.lengthOfLongestSubstring(s);
        // verify
        assertEquals(3, length);
    }

}