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

}
