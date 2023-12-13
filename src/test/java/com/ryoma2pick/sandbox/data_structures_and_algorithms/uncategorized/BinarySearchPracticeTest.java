package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchPracticeTest {

    @Test
    void fractional_part_is_truncated_when_assigning_to_int_variable() {
        int frac = 5 / 2; // 2.5
        assertEquals(2, frac);
    }

    @Test
    void return_integer_mid_when_size_is_odd() {
        int mid = BinarySearchPractice.mid(1, 3);
        assertEquals(2, mid);
    }

    @Test
    void return_integer_smaller_mid_when_size_is_even() {
        int mid = BinarySearchPractice.mid(1, 4);
        assertEquals(2, mid);
    }

    @Test
    void traverse_all_the_indices() {
        List<Integer> visited = new ArrayList<>();
        int start = 3;
        int end = 17;
        try {
            BinarySearchPractice.visitAllIndices(start, end, visited);
            System.out.println(visited.toString());
        } catch (StackOverflowError e) {
            fail();
        }
    }

    @Test
    void traverse_all_the_indices_stackOverFlow_left() {
        List<Integer> visited = new ArrayList<>();
        int start = 3;
        int end = 17;
        try {
            BinarySearchPractice.visitStackOverFlowLeft(start, end, visited);
            fail();
        } catch (StackOverflowError e) {
        }
    }

    @Test
    void traverse_all_the_indices_stackOverFlow_right() {
        List<Integer> visited = new ArrayList<>();
        int start = 3;
        int end = 17;
        try {
            BinarySearchPractice.visitStackOverFlowRight(start, end, visited);
            fail();
        } catch (StackOverflowError e) {
        }
    }

}