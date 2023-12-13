package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import java.util.Stack;

public class TreeDFS {

    public <T> void dfsRecursion(LinkedNode<T> root) {
        /*
        1. select a node
        2. select a child
          2.1 if the current node doesn't have any unvisited children,
              backtrack to the last node with unvisited children
          2.2 otherwise, repeat those operations
         */
        dfsRecursionHelper(root);
    }

    private <T> void dfsRecursionHelper(LinkedNode<T> root) {
        if (root == null) return;
        for (LinkedNode<T> child : root.getChildren()) {
            printNode(child);
            dfsRecursionHelper(child);
        }
    }

    public <T> void dfsStack(LinkedNode<T> root) {
        /*
        1. choose a node
        2. chose a child
          2.1 if the current node doesn't have any unvisited children,
              backtrack to the last node with unvisited children
          2.2 otherwise, repeat those operations
        */
        if (root == null) return;

        Stack<LinkedNode> stack = new Stack<>();
        LinkedNode<T> node = root;
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            printNode(node);
            for (LinkedNode<T> child : node.getChildren()) {
                stack.push(child);
            }
        }
    }

    private static <T> void printNode(LinkedNode<T> child) {
        System.out.println(child + " has been visited");
    }

}
