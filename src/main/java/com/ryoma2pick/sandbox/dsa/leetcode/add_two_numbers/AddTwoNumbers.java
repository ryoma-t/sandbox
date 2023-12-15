package com.ryoma2pick.sandbox.dsa.leetcode.add_two_numbers;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0);

        ListNode current = l;
        ListNode current1 = l1;
        ListNode current2 = l2;

        int carryUp = 0;

        while (true) {
            int sum = current1.val + current2.val + carryUp;
            if (sum < 10) {
                current.val += sum;
                carryUp = 0;
            } else {
                current.val += sum - 10;
                carryUp = 1;
            }

            if (current1.next == null && current2.next == null && carryUp == 0) {
                break;
            }

            current1 = current1.next != null ? current1.next : new ListNode(0);
            current2 = current2.next != null ? current2.next : new ListNode(0);

            current.next = new ListNode();
            current = current.next;
        }
        return l;
    }

}
