package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.AdjacencyListGraph;
import com.ryoma2pick.sandbox.dsa.graph.datastructure.Node;

import java.util.*;

public class DFSAdjacencyList {

    /*
    1. visit root
    2. choose an adjacent node
      2.1. if we've already visited it,
           backtrack to the previous node and choose another adjacent node
      2.2. if there are no unvisited adjacent nodes,
           backtrack to the last node with unvisited
      2.3. otherwise, visit it, and repeat those operations recursively
     */
    public static void dfsRecursion(Node root, AdjacencyListGraph graph) {
        Set<Node> visited = new HashSet<>();
        dfsRecursionHelper(root, graph, visited);
    }

    private static void dfsRecursionHelper(Node src, AdjacencyListGraph graph, Set<Node> visited) {
        if (visited.contains(src)) return;

        visited.add(src);
        System.out.println(src);

        for (Node dest : graph.getAdjList().get(src)) {
            dfsRecursionHelper(dest, graph, visited);
        }
    }

    public static void dfsStack(Node root, AdjacencyListGraph graph) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        Node src = root;
        stack.push(src);

        while (!stack.isEmpty()) {
            src = stack.pop();
            if (visited.contains(src)) continue;

            visited.add(src);
            System.out.println(src);

            for (Node dest : graph.getAdjList().get(src)) {
                stack.push(dest);
            }
        }

    }

}
