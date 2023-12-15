package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.AdjacencyMatrixGraph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class CycleDetection {

    public static boolean existsCycle(AdjacencyMatrixGraph graph) {
        Set<Integer> unvisited = new HashSet<>();
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        IntStream.range(0, graph.getSize()).forEach(unvisited::add);
        for (int src = 0; src < graph.getSize(); src++) {
            if (!unvisited.contains(src)) continue;
            if (existsCycleHelper(src, graph, unvisited, visiting, visited)) return true;
        }
        return false;
    }

    private static boolean existsCycleHelper(int src, AdjacencyMatrixGraph graph, Set<Integer> unvisited, Set<Integer> visiting, Set<Integer> visited) {
        unvisited.remove(src);
        visiting.add(src);

        for (int dst = 0; dst < graph.getSize(); dst++) {
            if (graph.getMatrix()[src][dst] == 0) continue;
            if (visited.contains(dst)) continue;
            if (visiting.contains(dst)) return true;
            return existsCycleHelper(dst, graph, unvisited, visiting, visited);
        }

        visiting.remove(src);
        visited.add(src);
        return false;
    }

}
