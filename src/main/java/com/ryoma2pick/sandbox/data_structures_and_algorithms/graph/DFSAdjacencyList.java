package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import java.util.*;

public class DFSAdjacencyList {

    public static <T> void dfsRecursion(T root, AdjacencyListGraph graph) {
        Set<T> visited = new HashSet<>();
        dfsRecursionHelper(root, graph, visited);
    }

    private static <T> void dfsRecursionHelper(T src, AdjacencyListGraph graph, Set<T> visited) {
        if (visited.contains(src)) return;

        visited.add(src);
        System.out.println(src);

        for (Node dest : graph.getGraph().get(new Node(src))) {
            if (visited.contains(dest)) continue;
            dfsRecursionHelper((T) dest.getValue(), graph, visited);
        }
    }

    public static <T> void dfsStack(T root, AdjacencyListGraph graph) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        T src = root;
        stack.push(src);

        while (!stack.isEmpty()) {
            src = stack.pop();
            if (visited.contains(src)) continue;

            visited.add(src);
            System.out.println(src);

            for (Node dest : graph.getGraph().get(new Node(src))) {
                if (visited.contains(dest)) continue;
                stack.push((T) dest.getValue());
            }
        }

    }

}
