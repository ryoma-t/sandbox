package com.ryoma2pick.sandbox.dsa_graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.dsa_graph.datastructure.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSorting {

    public static List<Node> sortDfs(AdjacencyMatrixGraph graph) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.getSize()];
        int src = 0;
        sortDfsHelper(graph, src, visited, stack);

        List<Node> sorted = new ArrayList<>();
        while (!stack.isEmpty()) {
            sorted.add(graph.getNodes().get(stack.pop()));
        }
        return sorted;
    }

    private static void sortDfsHelper(AdjacencyMatrixGraph graph, int src, boolean[] visited, Stack<Integer> stack) {
        if (visited[src]) return;

        visited[src] = true;
        for (int dst = 0; dst < graph.getMatrix()[src].length; dst++) {
            if (graph.getMatrix()[src][dst] == 0) continue;
            sortDfsHelper(graph, dst, visited, stack);
        }
        stack.push(src);
    }

}
