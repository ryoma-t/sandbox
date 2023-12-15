package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.dsa.graph.datastructure.Node;
import com.ryoma2pick.sandbox.dsa.graph.exception.OutOfSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DFSAdjacencyMatrixTest {

    private AdjacencyMatrixGraph graph;

    @Test
    void dfsRecursion() {
        graph.printGraph();
        DFSAdjacencyMatrix.dfsRecursion(graph);
    }

    @Test
    void dfsStack() {
        graph.printGraph();
        DFSAdjacencyMatrix.dfsStack(graph);
    }

    @BeforeEach
    private void graph() throws OutOfSizeException {
        graph = new AdjacencyMatrixGraph(5);
        graph.addNode(new Node<Character>('A'));
        graph.addNode(new Node<Character>('B'));
        graph.addNode(new Node<Character>('C'));
        graph.addNode(new Node<Character>('D'));
        graph.addNode(new Node<Character>('E'));
        graph.addEdge(0, 1);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 2);
    }

}