package com.ryoma2pick.sandbox.dsa_graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.AdjacencyMatrixGraph;
import com.ryoma2pick.sandbox.dsa_graph.datastructure.Node;

import java.util.*;

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

    public static List<Node> sortKahn(AdjacencyMatrixGraph dag) {
        Map<Integer, Integer> indegrees = new HashMap<>();
        for (int dst = 0; dst < dag.getSize(); dst++) {
            int indegree = 0;
            for (int src = 0; src < dag.getSize(); src++) {
                indegree += dag.getMatrix()[src][dst];
            }
            indegrees.put(dst, indegree);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegrees.keySet()) {
            if (indegrees.get(key) != 0) continue;
            queue.add(key);
        }

        List<Node> sorted = new LinkedList<>();
        while (!queue.isEmpty()) {
            int zeroIndegreeSrc = queue.poll();
            sorted.add(dag.getNodes().get(zeroIndegreeSrc));

            for (int dst = 0; dst < dag.getSize(); dst++) {
                if (dag.getMatrix()[zeroIndegreeSrc][dst] == 0) continue;
                indegrees.put(dst, indegrees.get(dst) - 1);
                if (indegrees.get(dst) == 0) queue.add(dst);
            }
        }

        return sorted;
    }

}
