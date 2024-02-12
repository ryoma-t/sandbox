package com.ryoma2pick.sandbox.dsa.graph.datastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    @Test
    void if_node_does_not_exist_then_add_successfully() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertEquals(0, graph.getAdjList().size());
        graph.addNode(new Node<String>("node"));
        assertEquals(1, graph.getAdjList().size());
    }

    @Test
    void if_the_node_already_exists_then_do_nothing() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        Node<Character> node = new Node<>('A');
        graph.addNode(node);
        graph.addNode(node);
        assertEquals(1, graph.getAdjList().size());
    }

    @Test
    void add_edge() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        Node nodeA = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addEdge(nodeA, nodeB);
        graph.addEdge(nodeA, nodeC);
        assertEquals(2, graph.getAdjList().get(nodeA).size());
        assertEquals(0, graph.getAdjList().get(nodeB).size());
        assertEquals(0, graph.getAdjList().get(nodeC).size());
    }

    @Test
    void print() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        Node nodeA = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        Node nodeD = new Node('D');
        Node nodeE = new Node('E');
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addEdge(nodeA, nodeB);
        graph.addEdge(nodeB, nodeE);
        graph.addEdge(nodeC, nodeD);
        graph.addEdge(nodeC, nodeE);
        graph.addEdge(nodeE, nodeA);
        graph.addEdge(nodeE, nodeC);
        graph.printGraph();
    }

}