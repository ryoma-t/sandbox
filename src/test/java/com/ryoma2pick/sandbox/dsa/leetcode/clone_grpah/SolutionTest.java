package com.ryoma2pick.sandbox.dsa.leetcode.clone_grpah;

import org.junit.jupiter.api.Test;
import com.ryoma2pick.sandbox.dsa.leetcode.clone_grpah.Solution.Node;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void if_node_is_null_then_return_empty_node() {
        assertEquals(null, new Solution().cloneGraph(null));
    }

    @Test
    void if_graph_is_only_one_node_then_return_that_node() {
        int value = 1;
        Node node = new Node(value);
        assertEquals(value, new Solution().cloneGraph(node).val);
    }

    @Test
    void if_root_has_two_children_then_copy_correctly() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);

        Node copied = new Solution().cloneGraph(node1);
        assertEquals(1, copied.val);
        assertEquals(2, copied.neighbors.get(0).val);
        assertEquals(3, copied.neighbors.get(1).val);
        assertEquals(0, copied.neighbors.get(0).neighbors.size());
        assertEquals(0, copied.neighbors.get(1).neighbors.size());

        assertTrue(copied != node1);
        assertTrue(copied.neighbors.get(0) != node2);
        assertTrue(copied.neighbors.get(1) != node3);
    }

    @Test
    void if_height_is_greater_than_2_then_copy_correctly() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);

        Node copied = new Solution().cloneGraph(node1);

        assertEquals(1, copied.val);
        assertEquals(2, copied.neighbors.get(0).val);
        assertEquals(3, copied.neighbors.get(0).neighbors.get(0).val);

        assertTrue(copied != node1);
        assertTrue(copied.neighbors.get(0) != node2);
        assertTrue(copied.neighbors.get(0).neighbors.get(0) != node3);
    }

    @Test
    void if_graph_is_cyclyc_then_copy_correctly() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.neighbors.add(node2);
        node2.neighbors.add(node3);
        node2.neighbors.add(node1);

        Node copied = new Solution().cloneGraph(node1);

        assertEquals(1, copied.val);
        assertEquals(1, copied.neighbors.size());
        assertEquals(2, copied.neighbors.get(0).val);

        assertEquals(2, copied.neighbors.get(0).neighbors.size());
        assertEquals(3, copied.neighbors.get(0).neighbors.get(0).val);
        assertEquals(1, copied.neighbors.get(0).neighbors.get(1).val);


        assertTrue(copied != node1);
        assertTrue(copied.neighbors.get(0).neighbors.get(0) != node3);
        assertTrue(copied.neighbors.get(0).neighbors.get(1) != node2);
    }

    @Test
    void create_graph() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
    }

}