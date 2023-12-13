package com.ryoma2pick.sandbox.data_structures_and_algorithms.pramp.sales_path;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    public static void main(String[] args) {
        /*********************************************
         * tree
         *********************************************/
        Node root = new Node(0);

        Node nodeLeft1 = new Node(5);
        Node nodeCenter1 = new Node(8);
        Node nodeRight1 = new Node(6);

        Node nodeLeft2 = new Node(1);
        Node nodeRight2 = new Node(0);

        root.children = new Node[]{nodeLeft1, nodeCenter1, nodeRight1};
        nodeLeft1.children = new Node[]{nodeLeft2, nodeRight2};

            /*
                       0
                    / | \
                   5  8  6
                  / \
                 1  0
             */

        /*********************************************
         * should return the minimum sum of all the descendants' costs
         *********************************************/
        // setup
        SalesPath salesPath = new SalesPath();
        // execute & verify
        System.out.println("smallestDescendantsCost should equal 5. Actual value is: " +
                salesPath.getCheapestCost(nodeLeft1));
        System.out.println("smallestDescendantsCost should equal 5. Actual value is: " +
                salesPath.getCheapestCost(root));
        System.out.println("smallestDescendantsCost should equal 1. Actual value is: " +
                salesPath.getCheapestCost(nodeLeft2));
    }

    static class Node {

        int cost;
        Node[] children;
        Node parent;

        Node(int cost) {
            this.cost = cost;
            children = null;
            parent = null;
        }

    }

    static class SalesPath {

        int getCheapestCost(Node rootNode) {
      /*
      1. choose a node (root)
      2. choose one child
         2.1 if the current node doesn't have any unvisited children,
              backtrack to the last node with unvisited children
         2.2 otherwise, repeat those operations
      */
            List<Integer> costs = new ArrayList<>();
            dfsHelper(rootNode, rootNode.cost, costs);

            int min = Integer.MAX_VALUE;
            for (int cost : costs) {
                min = Math.min(min, cost);
            }
            return min;
        }

        static void dfsHelper(Node rootNode, int cost, List<Integer> costs) {
            if (rootNode.children == null) {
                costs.add(cost);
                return;
            }
            for (Node child : rootNode.children) {
                dfsHelper(child, cost + child.cost, costs);
            }
        }

    }

}