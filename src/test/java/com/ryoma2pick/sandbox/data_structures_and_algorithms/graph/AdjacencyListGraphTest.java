package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    @Test
    void addNode() throws DuplicateException {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertEquals(0, graph.getGraph().size());
        graph.addNode('A');
        assertEquals(1, graph.getGraph().size());
    }

    @Test
    void addEdge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addNode('A');
        graph.addEdge('A', 'B');
        graph.addEdge('A', 'C');
        assertEquals(new Node('B'), graph.getGraph().get(new Node('A')).get(0));
        assertEquals(new Node('C'), graph.getGraph().get(new Node('A')).get(1));
    }

    @Test
    void print() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        graph.addNode('A');
        graph.addNode('B');
        graph.addNode('C');
        graph.addNode('D');
        graph.addNode('E');
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'D');
        graph.addEdge('C', 'E');
        graph.addEdge('E', 'A');
        graph.addEdge('E', 'C');

        graph.printGraph();
    }

}