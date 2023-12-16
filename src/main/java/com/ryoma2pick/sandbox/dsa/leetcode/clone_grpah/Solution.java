package com.ryoma2pick.sandbox.dsa.leetcode.clone_grpah;

import java.util.*;

public class Solution {

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Integer, Node> copiedMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        // 1. create a copy of the root node
        Node copied = new Node(node.val);
        // 9. keep track of references to the recopied nodes
        copiedMap.put(node.val, copied);

        // 3. since a graph could be cyclic, we need to keep track of the nodes that we've already visited
        visited.add(node.val);

        // 2. create copies of its adjacent neighbors, and add them to the copied parent recursively
        // 4. let's call our recursive function just helper
        //    we want it to take in parameters like this
        helper(node, copied, copiedMap, visited);
        return copied;
    }

    // 5. let's get to implementation
    //    the main idea is in each recursion of plain DFS, associate each copied node with its copied neighbors
    private static void helper(Node node, Node copied, Map<Integer, Node> copiedMap, Set<Integer> visited) {

        for (Node nei : node.neighbors) {
            if (!visited.contains(nei.val)) {
                // 6. if the adjacent neighbor hasn't yet been visited,
                //    what we're gonna do is create its copy and associate it with its copied parent
                Node copiedNei = new Node(nei.val);
                // 9. keep track of references to the recopied nodes
                copiedMap.put(nei.val, copiedNei);
                copied.neighbors.add(copiedNei);
                // 7. apply those operations recursively
                visited.add(nei.val);
                helper(nei, copiedNei, copiedMap, visited);
            } else {
                // 8. if the adjacent neighbor has already been visited,
                //    we need to associate its existing copy with its copied parent
                //    how? that's where we have to keep track of the references to copied nodes
                copied.neighbors.add(copiedMap.get(nei.val));

            }
        }

    }

    static class Node {

        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

    }

}
