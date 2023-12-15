package com.ryoma2pick.sandbox.dsa.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void sort() {
        // setup
        int[] list = new int[]{1, 4, 6, 3, 7, 8, 5};
        int[] partitionedList = new int[]{1, 3, 4, 5, 6, 7, 8};
        // execute
        QuickSort.sort(list);
        // verify
        assertTrue(Arrays.equals(partitionedList, list));
    }

    @Test
    void partition() {
        // setup
        int[] list = new int[]{1, 4, 6, 3, 7, 8, 5};
        int[] partitionedList = new int[]{1, 4, 3, 5, 7, 8, 6};
        // execute
        int pivotIndex = QuickSort.partition(list, 0, list.length - 1);
        // verify
        assertEquals(3, pivotIndex);
        assertTrue(Arrays.equals(partitionedList, list));
    }

    @Test
    void swap() {
        // setup
        int[] list = new int[]{1, 4, 3, 6, 7, 8, 5};
        int[] partitionedList = new int[]{1, 4, 3, 5, 7, 8, 6};
        // execute
        QuickSort.swap(list, 3, 6);
        // verify
        assertTrue(Arrays.equals(partitionedList, list));
    }

}