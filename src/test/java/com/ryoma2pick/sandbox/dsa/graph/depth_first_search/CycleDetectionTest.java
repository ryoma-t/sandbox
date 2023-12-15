package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.Node;
import com.ryoma2pick.sandbox.dsa.graph.datastructure.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.dsa.graph.exception.OutOfSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CycleDetectionTest {

    private AdjacencyMatrixGraph cyclic;
    private AdjacencyMatrixGraph acyclic;

    @Test
    void existsCycle() {
        assertEquals(true, CycleDetection.existsCycle(cyclic));
        assertEquals(false, CycleDetection.existsCycle(acyclic));
    }

    @BeforeEach
    private void cyclic() throws OutOfSizeException {
        cyclic = new AdjacencyMatrixGraph(4);
        cyclic.addNode(new Node<Character>('A'));
        cyclic.addNode(new Node<Character>('B'));
        cyclic.addNode(new Node<Character>('C'));
        cyclic.addNode(new Node<Character>('D'));
        cyclic.addEdge(0, 2);
        cyclic.addEdge(2, 1);
        cyclic.addEdge(2, 3);
        cyclic.addEdge(3, 0);
    }
    @BeforeEach
    private void acyclic() throws OutOfSizeException {
        acyclic = new AdjacencyMatrixGraph(4);
        acyclic.addNode(new Node<Character>('A'));
        acyclic.addNode(new Node<Character>('B'));
        acyclic.addNode(new Node<Character>('C'));
        acyclic.addNode(new Node<Character>('D'));
        acyclic.addEdge(0, 2);
        acyclic.addEdge(2, 1);
        acyclic.addEdge(2, 3);
    }

}