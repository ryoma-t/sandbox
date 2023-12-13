package com.ryoma2pick.sandbox.data_structures_and_algorithms.leetcode.zigzag_conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZigzagConversionTest {

    @Test
    void name() {
        // setup
        ZigzagConversion solution = new ZigzagConversion();
        // execute & verify
        assertEquals("PINALSIGYAHRPI",
                solution.convert("PAYPALISHIRING", 4));
    }

}