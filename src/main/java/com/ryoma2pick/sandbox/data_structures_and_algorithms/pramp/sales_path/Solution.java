package org.pramp.sales_path;

public class Solution {

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

        public int getCheapestCost(Node rootNode) {
            if (rootNode.children == null) return rootNode.cost;
            int min = Integer.MAX_VALUE;
            for (Node child : rootNode.children) {
                int cost = getCheapestCost(child);
                min = Math.min(min, cost);
            }
            return rootNode.cost + min;
        }

        /*********************************************
         * Driver program to test above method     *
         *********************************************/
    }

}