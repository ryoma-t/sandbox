package org.leetcode.add_two_numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddTwoNumbersTest {

    @Test
    void addTwoNumbersShouldReturnOutput() {
        // setup
        // nodeX
        ListNode nodeX1 = new ListNode(2);
        ListNode nodeX2 = new ListNode(4);
        ListNode nodeX3 = new ListNode(3);
        nodeX1.next = nodeX2;
        nodeX2.next = nodeX3;
        // nodeY
        ListNode nodeY1 = new ListNode(5);
        ListNode nodeY2 = new ListNode(6);
        ListNode nodeY3 = new ListNode(4);
        nodeY1.next = nodeY2;
        nodeY2.next = nodeY3;

        // execute
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode output = addTwoNumbers.addTwoNumbers(nodeX1, nodeY1);

        // verify
        assertEquals(7, output.val);
        assertEquals(0, output.next.val);
        assertEquals(8, output.next.next.val);
        assertNull(output.next.next.next);
    }

    @Test
    void addTwoNumbersShouldReturnOutputWhenTheLengthsAreDifferent() {
        // setup
        // nodeX
        ListNode nodeX1 = new ListNode(9);
        ListNode nodeX2 = new ListNode(9);
        ListNode nodeX3 = new ListNode(9);
        ListNode nodeX4 = new ListNode(9);
        ListNode nodeX5 = new ListNode(9);
        ListNode nodeX6 = new ListNode(9);
        ListNode nodeX7 = new ListNode(9);
        nodeX1.next = nodeX2;
        nodeX2.next = nodeX3;
        nodeX3.next = nodeX4;
        nodeX4.next = nodeX5;
        nodeX5.next = nodeX6;
        nodeX6.next = nodeX7;
        // nodeY
        ListNode nodeY1 = new ListNode(9);
        ListNode nodeY2 = new ListNode(9);
        ListNode nodeY3 = new ListNode(9);
        ListNode nodeY4 = new ListNode(9);
        nodeY1.next = nodeY2;
        nodeY2.next = nodeY3;
        nodeY3.next = nodeY4;

        // execute
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode output = addTwoNumbers.addTwoNumbers(nodeX1, nodeY1);

        // verify
        ListNode current = output;
        int[] expectedArray = new int[]{8, 9, 9, 9, 0, 0, 0, 1};
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], current.val);
            current = current.next;
            if (i == expectedArray.length - 1) {
                assertNull(current);
            }
        }
    }

    @Test
    void zeroPlusZeroShouldReturnZero() {
        // setup
        // nodeX
        ListNode nodeX1 = new ListNode(0);
        // nodeY
        ListNode nodeY1 = new ListNode(0);

        // execute
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode output = addTwoNumbers.addTwoNumbers(nodeX1, nodeY1);

        // verify
        assertEquals(0, output.val);
        assertNull(output.next);
    }

}