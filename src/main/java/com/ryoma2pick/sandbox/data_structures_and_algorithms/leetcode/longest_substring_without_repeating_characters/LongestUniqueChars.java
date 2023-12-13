package org.leetcode.longest_substring_without_repeating_characters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestUniqueChars {

    public int lengthOfLongestSubstring(String s) {
        char[] sArray = s.toCharArray();
        Set<Character> charCount = new HashSet<>();
        int maxLength = 0;

        int l = 0;
        int r = 0;
        char lKey;
        char rKey;

        while (r < sArray.length) {
            rKey = sArray[r];
            if (!charCount.contains(rKey)) {
                charCount.add(rKey);
                maxLength = Math.max(maxLength, r - l + 1);
                r++;
                continue;
            }
            while (charCount.contains(rKey)) {
                lKey = sArray[l];
                charCount.remove(lKey);
                l++;
            }
        }
        return maxLength;
    }

}
