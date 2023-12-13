package org.leetcode.zigzag_conversion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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