package com.ryoma2pick.sandbox.practice;

public class WordsPalindromes {

    public boolean solution(String[] arr) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        String prevL = arr[l];
        String prevR = arr[r];
        return helper(prevL, prevR, l, r, arr);
    }

    private boolean helper(String prevL, String prevR, int l, int r, String[] arr) {
        if (l == r) return true;
        if (l + 1 == r) {
            String modifiedL;
            String modifiedR;

            // do nothing
            if (prevL.equals(prevR)) return true;

            // left[rightmost] -> right
            modifiedL = prevL.substring(0, prevL.length() - 1);
            modifiedR = prevL.charAt(prevL.length() - 1) + prevR;
            if (modifiedL.equals(modifiedR)) return true;

            // left <- right[leftmost]
            modifiedL = prevL + prevR.charAt(0);
            modifiedR = prevR.substring(1, prevR.length());
            if (modifiedL.equals(modifiedR)) return true;

            return false;
        }

        String modifiedPrevL;
        String modifiedCurrentL;
        String modifiedPrevR;
        String modifiedCurrentR;

        // l: do nothing
        modifiedPrevL = prevL;
        modifiedCurrentL = arr[l + 1];
        // r: do nothing
        modifiedPrevR = prevR;
        modifiedCurrentR = arr[r - 1];
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;
        // r: current <- prev[leftmost]
        modifiedPrevR = prevR.substring(1, prevR.length());
        modifiedCurrentR = arr[r - 1] + prevR.charAt(0);
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;
        // r: current[rightmost] -> prev
        modifiedPrevR = arr[r - 1].charAt(arr[r - 1].length() - 1) + prevR;
        modifiedCurrentR = arr[r - 1].substring(0, arr[r - 1].length() - 1);
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;

        // l: prev[leftmost] -> current
        modifiedPrevL = prevL.substring(0, prevL.length() - 1);
        modifiedCurrentL = prevL.charAt(prevL.length() - 1) + arr[l + 1];
        // r: do nothing
        modifiedPrevR = prevR;
        modifiedCurrentR = arr[r - 1];
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;
        // r: current <- prev[leftmost]
        modifiedPrevR = prevR.substring(1, prevR.length());
        modifiedCurrentR = arr[r - 1] + prevR.charAt(0);
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;
        // r: current[rightmost] -> prev
        modifiedPrevR = arr[r - 1].charAt(arr[r - 1].length() - 1) + prevR;
        modifiedCurrentR = arr[r - 1].substring(0, arr[r - 1].length() - 1);
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;

        // l: prev <- current[rightmost]
        modifiedPrevL = prevL + arr[l + 1].charAt(0);
        modifiedCurrentL = prevR;
        // r: do nothing
        modifiedPrevR = arr[l + 1].substring(1, arr[l + 1].length());
        modifiedCurrentR = arr[r - 1];
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;
        // r: current <- prev[leftmost]
        modifiedPrevR = prevR.substring(1, prevR.length());
        modifiedCurrentR = arr[r - 1] + prevR.charAt(0);
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;
        // r: current[rightmost] -> prev
        modifiedPrevR = arr[r - 1].charAt(arr[r - 1].length() - 1) + prevR;
        modifiedCurrentR = arr[r - 1].substring(0, arr[r - 1].length() - 1);
        if (modifiedPrevR.equals(modifiedPrevR)
                && helper(modifiedCurrentL, modifiedCurrentR, l + 1, r - 1, arr)) return true;

        return false;
    }

}
/*
---
time complexity: O(m * n)
    m: max length of str in arr
    n: size of arr
space complexity: O(1)


---
   l                         r
["aa", "bab", "cde", "aba", "ab"]
   0    1      2       3      4

         l              r
["aab", "ab", "cde", "ab", "aab"]
   0    1      2       3      4

---
declare
    prevL: string
    prevR: string
    l: int
    r: int
    n: the length of arr

invoke the function helper(prevL=arr[0], prevR=arr[n-1], l=0, r=n-1)
    if l == r, return true
    if l - 1  == r,
        if there exists any operation after which two successive strings are identical, return true
        otherwise, return false

    // l: do nothing
        // r: do nothing
        prevL = prevL
        currentL = arr[l+1]
        prevR = prevR
        currentR = arr[r-1]
        if(prevL == prevR && helper(currentL, currentR, l+1, r-1)), return true

        // r: current <- prev[leftmost]
        prevL = prevL
        currentL = arr[l+1]
        prevR = prevR.substring(1,rightmost)
        currentR = arr[r-1] + prevR[leftmost]
        if(prevL == prevR && helper(currentL, currentR, l+1, r-1)), return true

        // r: current[rightmost] -> prev
        prevL = prevR
        currentL = arr[l+1]
        prevR = arr[r-1][rightmost] + prev
        currentR = arr[r-1].substring(0,rightmost-1)
        if(prevL == prevR && helper(currentL, currentR, l+1, r-1)), return true

    // l: prev[rightmost] -> current
        // do nothing
        ...
        // r: current <- prev[leftmost]
        ...
        // r: current[rightmost] -> prev
        ...

    // l: prev <- current[leftmost]
        // do nothing
        ...
        // r: current <- prev[leftmost]
        ...
        // r: current[rightmost] -> prev
        ...

    otherwise, return false
 */
