package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.AdjacencyListGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DFSAdjacencyListTest {

    private AdjacencyListGraph graph;

    @Test
    void dfsRecursion() {
        DFSAdjacencyList.dfsRecursion('A', graph);
    }

    @Test
    void dfsStack() {
        DFSAdjacencyList.dfsStack('A', graph);
    }

    @BeforeEach
    void setup() {
        graph = new AdjacencyListGraph();
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