package com.ryoma2pick.sandbox.dsa.graph.datastructure;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class AdjacencyListGraph {

    private Map<Node, List<Node>> graph = new HashMap<>();

    public <T> void addNode(T data) {
        graph.put(new Node(data), new ArrayList<>());
    }

    public <T> void addEdge(T src, T dest) {
        graph.get(new Node(src)).add(new Node(dest));
    }

    public void printGraph() {
        for (Node node : graph.keySet()) {
            System.out.print(node.getValue() + ": ");
            StringBuilder sb = new StringBuilder();
            for (Node neighbor : graph.get(node)) {
                sb.append(neighbor.getValue() + " ");
            }
            System.out.println(sb.toString());
        }
    }

}
