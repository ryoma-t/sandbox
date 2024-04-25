package com.ryoma2pick.sandbox.practice;

/*
An array is called palindromic if it remains the same after reversing the order of its elements.

You have an array of strings arr.
For each i, arr[i] consists of at least two characters.
For each pair of consecutive elements arr[i] and arr[i + 1], you can:
    Move the rightmost character of arr[i] to the leftmost position in arr[i + 1].
        For instance, "abc" and "def" will become "ab" and "cdef". This operation can be applied only once to any pair of consecutive elements.
    Move the leftmost character of arr[i + 1] to the rightmost position in arr[i].
        For instance, "abc" and "def" will become "abcd" and "ef". Again, this operation can be applied only once to any pair of consecutive elements.
    Do nothing to the pair of consecutive elements.
Is it possible to obtain a palindromic array from arr by performing these operations?

Example
For arr = ["aa", "bab", "cde", "aba", "ab"], the output should be
    solution(arr) = true.
    Apply the second operation to "aa" and "bab".
    Apply the first operation to "aba" and "ab".
    This gives us the following array: ["aab", "ab", "cde", "ab", "aab"], which is palindromic.

For arr = ["palindrome"], the output should be
    solution(arr) = true.
    The given array is already palindromic, so no operations are needed.

For arr = ["aaaaaa", "aa"], the output should be
    solution(arr) = false.
    If moving two characters between two consecutive array elements was allowed, the array could have been made palindromic,
    but this is impossible given the actual rules.
 */
public class WordsPalindromes {

    public static void main(String[] args) {
        System.out.println(WordsPalindromes.solution(new String[]{"aa", "bab", "cde", "aba", "ab"})); // true
        System.out.println(WordsPalindromes.solution(new String[]{"palindrome"})); // true
        System.out.println(WordsPalindromes.solution(new String[]{"aaaaaa", "aa"})); // false
        System.out.println(WordsPalindromes.solution(new String[]{"aaaa", "aa"})); // true
    }

    private static boolean solution(String[] array) {
        if (array.length <= 1) return true;

        int n = array.length;
        int l = 0;
        int r = n - 1;
        String prevL = array[l];
        String prevR = array[r];
        return helper(prevL, prevR, l, r, array);
    }

    private static boolean helper(String prevL, String prevR, int l, int r, String[] array) {
        if (l == r) return true;
        if (l + 1 == r) {
            // do nothing
            if (array[l].equals(array[r])) return true;
            // currentL <- currentR[leftmost]
            if ((array[l] + array[r].charAt(0)).equals(array[r].substring(1, array[r].length()))) return true;
            // currentL[rightmost] -> currentR
            if (array[l].substring(0, array[l].length() - 1).equals(array[l].charAt(array[l].length() - 1) + array[r]))
                return true;
            return false;
        }

        String modifiedPrevL;
        String modifiedCurrentL;
        String modifiedPrevR;
        String modifiedCurrentR;

        // l: do nothing
        modifiedPrevL = prevL;
        modifiedCurrentL = array[l + 1];
        //// r: do nothing
        modifiedPrevR = prevR;
        modifiedCurrentR = array[r - 1];
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;
        //// r: currentR <- prevR[leftmost]
        modifiedPrevR = prevR.substring(1, prevR.length());
        modifiedCurrentR = array[r - 1] + prevR.charAt(0);
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;
        //// r: currentR[rightmost] -> prevR
        modifiedPrevR = array[r - 1].charAt(array[r - 1].length() - 1) + prevR;
        modifiedCurrentR = array[r - 1].substring(0, array[r].length() - 1);
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;

        // l: prevL[rightmost] -> currentL
        modifiedPrevL = prevL.substring(0, prevL.length() - 1);
        modifiedCurrentL = prevL.charAt(prevL.length() - 1) + array[l + 1];
        //// r: do nothing
        modifiedPrevR = prevR;
        modifiedCurrentR = array[r - 1];
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;
        //// r: currentR <- prevR[leftmost]
        modifiedPrevR = prevR.substring(1, prevR.length());
        modifiedCurrentR = array[r - 1] + prevR.charAt(0);
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;
        //// r: currentR[rightmost] -> prevR
        modifiedPrevR = array[r - 1].charAt(array[r - 1].length() - 1) + prevR;
        modifiedCurrentR = array[r - 1].substring(0, array[r - 1].length() - 1);
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;

        // l: prevL <- currentL[leftmost]
        modifiedPrevL = prevL + array[l + 1].charAt(0);
        modifiedCurrentL = array[l + 1].substring(1, array[l + 1].length());
        //// r: do nothing
        modifiedPrevR = prevR;
        modifiedCurrentR = array[r - 1];
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;
        //// r: currentR <- prevR[leftmost]
        modifiedPrevR = prevR.substring(1, prevR.length());
        modifiedCurrentR = array[r - 1] + prevR.charAt(0);
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;
        //// r: currentR[rightmost] -> prevR
        modifiedPrevR = array[r - 1].charAt(array[r - 1].length() - 1) + prevR;
        modifiedCurrentR = array[r - 1].substring(0, array[r - 1].length() - 1);
        if (modifiedPrevL.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, array)) return true;

        return false;
    }

}

/*
algorithm
---
declare
    n: size of array
    l: 1
    r: n-2
    prevL = array[0]
    prevR= array[n-1]

invoke the function helper(prevL, prevR, l, r, array) recursively
    if l == r, return true
    if l + 1 == r,
        if arr[l] == arr[r], return true
        if currentL <- currentR[leftmost], return true
        if currentL[rightmost] -> currentR, return true
        otherwise, return false
    // l: do nothing
        // r: do nothing
        if modifiedPrevL == modifiedPrevR,
            and helper(prevL=currentL, prevR=currentR, l+1, r-1, arr), return true
        // r: currentR <- prevR[leftmost]
        // r: currentR[rightmost] -> prevR
    // l: prevL[rightmost] -> currentL
        // r: do nothing
        // r: currentR <- prevR[leftmost]
        // r: currentR[rightmost] -> prev
    // l: prevL <- currentL[leftmost]
        // r: do nothing
        // r: currentR <- prevR[leftmost]
        // r: currentR[rightmost] -> prevR
    otherwise, return false


example
---
 prevL                      prevR
["aa", "bab", "cde", "aba", "ab"]
        currentL     currentR

prevL = aa
currentL = bab
(prevL <- currentL[lefttmost])
modifiedPrevL = aab
modifiedCurrentL = ab

prevR = ab
currentR = aba
(currentR[rightmost] -> prevR)
modifiedPrevR = aab
modifiedCurrentR = ab

["aab", "ab", "cde", "ab", "aab"]


main idea
---
for a pair of prevL and currentL, successive strings, there are 3 types op operations to modify them
for a pair of prevR and currentR, successive strings, there are 3 types op operations to modify them
for each pair of modified prevL, currentL, prevR and prevR of 9 = 3 * 3 patterns,
    if prevL == prevR, invoke function recursively with l forward and r backward


complexity
---
time complexity: O(3^(n/2) * m)
    n: size of array
    m: max length of str in array
    worst case: [aa, aa, aa, aa, aa]
space complexity: O(1)
 */


