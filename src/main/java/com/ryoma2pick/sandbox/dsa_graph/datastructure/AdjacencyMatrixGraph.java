package com.ryoma2pick.sandbox.dsa_graph.datastructure;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.Node;
import com.ryoma2pick.sandbox.dsa_graph.exception.OutOfSizeException;
import lombok.Data;

import java.util.*;

@Data
public class AdjacencyMatrixGraph {

    private List<Node> nodes;
    private int[][] matrix;
    private int size;

    public AdjacencyMatrixGraph(int size) {
        this.size = size;
        this.nodes = new ArrayList<>();
        this.matrix = new int[size][size];
    }

    public void addNode(Node node) throws OutOfSizeException {
        if (nodes.size() >= size) {
            throw new OutOfSizeException();
        }
        nodes.add(node);
    }

    public void addEdge(int i, int j) throws OutOfSizeException {
        if (i >= size || j >= size) {
            throw new OutOfSizeException();
        }
        matrix[i][j] = 1;
    }

    public List<Node> topologicalSortDfs() {
        /*
        1. choose a node
        2. choose an adjacent node
        3. if the node doesn't have any unvisited adjacent neighbors,
           add the node to a stack, and
           backtrack to the last node with unvisited adjacent neighbors
         */
        boolean[] visited = new boolean[size];
        Stack<Integer> stack = new Stack<>();
        topologicalSortDfsHelper(0, visited, stack);

        List<Node> output = new ArrayList<>();
        while (!stack.isEmpty()) {
            output.add(nodes.get(stack.pop()));
        }
        return output;

    }

    void topologicalSortDfsHelper(int src, boolean[] visited, Stack<Integer> stack) {
        if (visited[src]) return;

        visited[src] = true;
        for (int dst = 0; dst < size; dst++) {
            if (matrix[src][dst] == 0) continue;
            topologicalSortDfsHelper(dst, visited, stack);
        }
        stack.push(src);
    }

    public List<Node> topologicalSortKahn() {
        /*
        1. calculate the indegree
        2. add zero-indegree nodes to a queue
        3. dequeue a node,
           append it to the list that we're going to return,
           decrement the neighbors' indegree,
           and add zero-indegree nodes to the queue
        4. repeat those operations as long as the queue is not empty
         */
        Map<Integer, Integer> indegrees = new HashMap<>();
        for (int dst = 0; dst < size; dst++) {
            int indegree = 0;
            for (int src = 0; src < size; src++) {
                indegree += matrix[src][dst];
            }
            indegrees.put(dst, indegree);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Integer key : indegrees.keySet()) {
            if (indegrees.get(key) == 0) queue.add(key);
        }

        List<Node> output = new ArrayList<>();
        while (!queue.isEmpty()) {
            int src = queue.poll();
            output.add(nodes.get(src));

            for (int dst = 0; dst < size; dst++) {
                if (matrix[src][dst] == 1) {
                    indegrees.put(dst, indegrees.get(dst) - 1);
                    if (indegrees.get(dst) == 0) queue.add(dst);
                }
            }
        }

        return output;
    }

    public void printGraph() {
        StringBuilder header = new StringBuilder();
        header.append("  ");
        for (int i = 0; i < nodes.size(); i++) {
            header.append(nodes.get(i) != null ? nodes.get(i).getValue() : "-");
            header.append(" ");
        }
        System.out.println(header.toString());
        for (int i = 0; i < nodes.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(nodes.get(i) != null ? nodes.get(i).getValue() : "-");
            sb.append(" ");
            for (int j = 0; j < nodes.size(); j++) {
                sb.append(matrix[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    private void printNode(int src) {
        System.out.println(nodes.get(src) + " has been visited");
    }

}
