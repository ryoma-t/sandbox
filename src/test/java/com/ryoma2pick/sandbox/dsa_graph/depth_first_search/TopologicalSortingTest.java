package com.ryoma2pick.sandbox.dsa_graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.dsa_graph.datastructure.Node;
import com.ryoma2pick.sandbox.dsa_graph.exception.OutOfSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class TopologicalSortingTest {

    private AdjacencyMatrixGraph dag;

    @Test
    void sortDfs() {
        dag.printGraph();
        List<Node> sorted = TopologicalSorting.sortDfs(dag);
        System.out.println(sorted.toString());
    }

    @Test
    void sortKahn() {
        dag.printGraph();
        List<Node> sorted = TopologicalSorting.sortKahn(dag);
        System.out.println(sorted.toString());
    }

    @BeforeEach
    private void directedAcyclicGraph() throws OutOfSizeException {
        dag = new AdjacencyMatrixGraph(7);
        dag.addNode(new Node<Character>('A'));
        dag.addNode(new Node<Character>('B'));
        dag.addNode(new Node<Character>('C'));
        dag.addNode(new Node<Character>('D'));
        dag.addNode(new Node<Character>('E'));
        dag.addNode(new Node<Character>('F'));
        dag.addNode(new Node<Character>('G'));
        dag.addEdge(0, 1);
        dag.addEdge(0, 2);
        dag.addEdge(1, 3);
        dag.addEdge(1, 4);
        dag.addEdge(2, 5);
        dag.addEdge(4, 5);
        dag.addEdge(4, 6);
        dag.addEdge(6, 3);
    }

}