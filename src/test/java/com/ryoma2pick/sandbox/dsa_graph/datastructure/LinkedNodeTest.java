package com.ryoma2pick.sandbox.dsa_graph.datastructure;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.LinkedNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedNodeTest {

    @Test
    void addChild() {
        LinkedNode<Integer> node1 = new LinkedNode<>(1);
        LinkedNode<Integer> node2 = new LinkedNode<>(2);
        LinkedNode<Integer> node3 = new LinkedNode<>(3);

        assertEquals(0, node1.getChildren().size());

        node1.addChild(node2);
        node1.addChild(node3);
        assertEquals(2, node1.getChildren().size());
        assertEquals(node2, node1.getChildren().get(0));
        assertEquals(node3, node1.getChildren().get(1));
    }

}