package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

/*
1. choose a node
2. choose an adjacent neighbor
  2.1. if we've already visited the current node,
       backtrack to the previous node and choose another unvisited node
  2.2. if the current node doesn't have any unvisited adjacent neighbor,
       backtrack to the last node with unvisited adjacent neighbor
  2.3. otherwise, repeat those operations
 */
public class DFSAdjacencyMatrix {

    // we're going to use a graph that utilizes an adjacency matrix
    // we're going to create a method
    // let's call it dfsRecursion
    // we want it take in one parameter, graph
    public static void dfsRecursion(AdjacencyMatrixGraph graph) {

        // to keep track of the nodes that we've already visited,
        // what we're gonna do is create an array of booleans
        boolean[] visited = new boolean[graph.getSize()];
        int src = 0;

        // we will call a helper function
        // let's call it dfsRecursionHelper
        // we want it to take in the parameters like this
        dfsRecursionHelper(src, graph.getMatrix(), visited);
        // let's get to the implementation
    }

    private static void dfsRecursionHelper(int src, int[][] matrix, boolean[] visited) {
        // if we've already visited the current node, we're going to return
        if (visited[src]) {
            return;
        }

        // otherwise, we will mark this node as visited
        visited[src] = true;
        // next, we need to find any adjacent neighbors
        // since we're using an adjacency matrix, we need to iterate over its column
        for (int dest = 0; dest < matrix[src].length; dest++) {
            // in each iteration, check if an element is 1,
            // which means that's an adjacent neighbor
            if (matrix[src][dest] == 1) continue;
            // then, we will call dfsRecursionHelper recursively
            dfsRecursionHelper(dest, matrix, visited);
        }
    }

}
