package com.ryoma2pick.sandbox.dsa.graph.datastructure;

import lombok.Data;

import java.util.*;

@Data
public class AdjacencyListGraph {

    private Map<Node, Set<Node>> adjList = new HashMap<>();

    public void addNode(Node node) {
        adjList.put(node, new HashSet<>());
    }

    public void addEdge(Node src, Node dest) {
        adjList.get(src).add(dest);
    }

    public void printGraph() {
        for (Node node : adjList.keySet()) {
            System.out.print(node.getValue() + ": ");
            StringBuilder sb = new StringBuilder();
            for (Node neighbor : adjList.get(node)) {
                sb.append(neighbor.getValue() + " ");
            }
            System.out.println(sb.toString());
        }
    }

}
