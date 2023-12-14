package com.ryoma2pick.sandbox.dsa_graph.datastructure;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.dsa_graph.datastructure.Node;
import com.ryoma2pick.sandbox.dsa_graph.exception.OutOfSizeException;
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
        graph.addNode(new Node('A'));
        graph.addNode(new Node('B'));
        graph.addNode(new Node('C'));
        graph.addEdge(1, 2);
        graph.printGraph();
    }

    @Test
    void topologicalSortKahn() throws OutOfSizeException {
        directedAcyclicGraph();
        List<Node> sorted = dag.topologicalSortKahn();
        System.out.println(sorted.toString());
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