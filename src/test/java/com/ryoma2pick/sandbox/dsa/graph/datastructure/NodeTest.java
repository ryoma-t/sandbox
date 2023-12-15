package com.ryoma2pick.sandbox.dsa.graph.datastructure;

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