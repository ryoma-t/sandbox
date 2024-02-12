package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.AdjacencyListGraph;
import com.ryoma2pick.sandbox.dsa.graph.datastructure.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DFSAdjacencyListTest {

    private AdjacencyListGraph graph;
    private Node root;

    @Test
    void dfsRecursion() {
        DFSAdjacencyList.dfsRecursion(root, graph);
    }

    @Test
    void dfsStack() {
        DFSAdjacencyList.dfsStack(root, graph);
    }

    @BeforeEach
    void setup() {
        graph = new AdjacencyListGraph();
        root = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        Node nodeD = new Node('D');
        Node nodeE = new Node('E');
        graph.addNode(root);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addEdge(root, nodeB);
        graph.addEdge(nodeB, nodeE);
        graph.addEdge(nodeC, nodeD);
        graph.addEdge(nodeC, nodeE);
        graph.addEdge(nodeE, root);
        graph.addEdge(nodeE, nodeC);

        graph.printGraph();
    }

}