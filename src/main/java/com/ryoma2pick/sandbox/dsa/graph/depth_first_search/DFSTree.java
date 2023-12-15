package com.ryoma2pick.sandbox.dsa.graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa.graph.datastructure.LinkedNode;

import java.util.Stack;

public class DFSTree {

    public static <T> void dfsRecursion(LinkedNode<T> root) {
        dfsRecursionHelper(root);
    }

    private static <T> void dfsRecursionHelper(LinkedNode<T> root) {
        System.out.println(root);
        if (root == null) return;
        for (LinkedNode<T> child : root.getChildren()) {
            dfsRecursionHelper(child);
        }
    }

    public static <T> void dfsStack(LinkedNode<T> root) {
        Stack<LinkedNode<T>> stack = new Stack<>();
        LinkedNode<T> node = root;
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.println(node);

            for (LinkedNode<T> child : node.getChildren()) {
                stack.push(child);
            }
        }
    }

}
