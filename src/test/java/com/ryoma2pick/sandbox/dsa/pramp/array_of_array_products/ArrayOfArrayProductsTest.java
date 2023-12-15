package com.ryoma2pick.sandbox.dsa.pramp.array_of_array_products;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayOfArrayProductsTest {

    @Test
    void ifInputIsEmptyItShouldReturnEmptyArray() {
        // setup
        int[] arr = new int[]{};
        // execute
        int[] output = ArrayOfArrayProducts.arrayOfArrayProducts(arr);
        // verify
        int[] expected = new int[]{};
        assertTrue(Arrays.equals(expected, output));
    }

    @Test
    void ifInputIsOneElementItShouldReturnEmptyArray() {
        // setup
        int[] arr = new int[]{1};
        // execute
        int[] output = ArrayOfArrayProducts.arrayOfArrayProducts(arr);
        // verify
        int[] expected = new int[]{};
        assertTrue(Arrays.equals(expected, output));
    }

    @Test
    void ifInputIsTwoElementsItShouldReturnTwoLengthArray() {
        // setup
        int[] arr = new int[]{1, 1};
        // execute
        int[] output = ArrayOfArrayProducts.arrayOfArrayProducts(arr);
        // verify
        int[] expected = new int[]{1, 1};
        assertTrue(Arrays.equals(expected, output));
    }

    @Test
    void shouldReturnProductsExceptTheIndex() {
        // setup
        int[] arr = new int[]{2, 7, 3, 4};
        // execute
        int[] output = ArrayOfArrayProducts.arrayOfArrayProducts(arr);
        // verify
        int[] expected = new int[]{84, 24, 56, 42};
        assertTrue(Arrays.equals(expected, output));
    }

    @Test
    void shouldReturnProductsOfIntegersToTheLeftOfIndex() {
        // setup
        int[] arr = new int[]{1, 2, 3};
        // execute
        int[] output = ArrayOfArrayProducts.productsToLeft(arr);
        // verify
        int[] expected = new int[]{1, 1, 2};
        assertTrue(Arrays.equals(expected, output));
    }

}