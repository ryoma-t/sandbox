package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import com.ryoma2pick.sandbox.data_structures_and_algorithms.graph.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NodeTest {

    @Test
    void constructor() {
        int value = 1;
        Node<Integer> node = new Node<>(value);
        assertEquals(value, node.getValue());
    }

}