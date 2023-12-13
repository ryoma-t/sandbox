package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import com.ryoma2pick.sandbox.data_structures_and_algorithms.graph.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.data_structures_and_algorithms.graph.OutOfSizeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AdjacencyMatrixGraphTest {

    private AdjacencyMatrixGraph graph;
    private AdjacencyMatrixGraph dag;

    @Test
    void constructor() {
        int size = 3;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(size);
        assertEquals(size, graph.getMatrix().length);
        assertEquals(size, graph.getMatrix()[0].length);
    }

    @Test
    void addNode() {
        int size = 1;
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(size);

        Node<Integer> node1 = new Node(1);
        try {
            graph.addNode(node1);
            assertEquals(node1, graph.getNodes().get(0));
        } catch (OutOfSizeException e) {
            fail();
        }

        Node<Integer> node2 = new Node(2);
        try {
            graph.addNode(node2);
            fail();
        } catch (OutOfSizeException e) {
        }
    }

    @Test
    void addEdge() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        assertEquals(0, graph.getMatrix()[1][2]);

        try {
            graph.addEdge(1, 2);
            assertEquals(1, graph.getMatrix()[1][2]);
        } catch (OutOfSizeException e) {
            fail();
        }

        try {
            graph.addEdge(1, 3);
            fail();
        } catch (OutOfSizeException e) {
        }
    }

    @Test
    void printGraph() throws OutOfSizeException {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(3);
        graph.addNode(new Node(1));
        graph.addNode(new Node(2));
        graph.addNode(new Node(3));
        graph.addEdge(1, 2);
        graph.printGraph();
    }

    @Test
    void dfsRecursion() throws OutOfSizeException {
        graph();
        graph.printGraph();
        graph.dfsRecursion();
    }

    @Test
    void dfsStack() throws OutOfSizeException {
        graph();
        graph.printGraph();
        graph.dfsStack();
    }

    @Test
    void topologicalSortDfs() throws OutOfSizeException {
        directedAcyclicGraph();
        List<Node> sorted = dag.topologicalSortDfs();
        System.out.println(sorted.toString());
    }

    @Test
    void topologicalSortKahn() throws OutOfSizeException {
        directedAcyclicGraph();
        List<Node> sorted=dag.topologicalSortKahn();
        System.out.println(sorted.toString());
    }

    private void graph() throws OutOfSizeException {
        graph = new AdjacencyMatrixGraph(5);
        graph.addNode(new Node<Character>('A'));
        graph.addNode(new Node<Character>('B'));
        graph.addNode(new Node<Character>('C'));
        graph.addNode(new Node<Character>('D'));
        graph.addNode(new Node<Character>('E'));
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 0);
        graph.addEdge(4, 2);
    }

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