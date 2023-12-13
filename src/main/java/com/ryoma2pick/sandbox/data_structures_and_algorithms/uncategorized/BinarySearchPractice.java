package org.example;

import java.util.Collections;
import java.util.List;

public class BinarySearchPractice {

    static int mid(int start, int endInclusive) {
        return start + (endInclusive - start) / 2;
    }

    static void visitAllIndices(int start, int end, List<Integer> visited) {
        if (start > end) return;

        int mid = mid(start, end);
        visited.add(mid);

        visitAllIndices(start, mid - 1, visited);
        visitAllIndices(mid + 1, end, visited);

        Collections.sort(visited);
    }

    static void visitStackOverFlowLeft(int start, int end, List<Integer> visited) {
        if (start > end) return;

        int mid = mid(start, end);
        visited.add(mid);

        visitStackOverFlowLeft(start, mid, visited);
        Collections.sort(visited);
    }

    static void visitStackOverFlowRight(int start, int end, List<Integer> visited) {
        if (start > end) return;

        int mid = mid(start, end);
        visited.add(mid);

        visitStackOverFlowRight(mid, end, visited);
        Collections.sort(visited);
    }

}
