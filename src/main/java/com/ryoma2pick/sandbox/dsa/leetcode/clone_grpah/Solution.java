package com.ryoma2pick.sandbox.dsa.leetcode.clone_grpah;

import java.util.*;

public class Solution {

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Integer, Node> copiedMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        Node copied = new Node(node.val);
        copiedMap.put(node.val, copied);
        visited.add(node.val);

        helper(node, copied, copiedMap, visited);
        return copied;
    }

    private static void helper(Node node, Node copied, Map<Integer, Node> copiedMap, Set<Integer> visited) {

        for (Node nei : node.neighbors) {
            if (visited.contains(nei.val)) {
                copied.neighbors.add(copiedMap.get(nei.val));
                continue;
            }
            Node copiedNei = new Node(nei.val);
            copiedMap.put(nei.val, copiedNei);
            copied.neighbors.add(copiedNei);
            visited.add(nei.val);
            helper(nei, copiedNei, copiedMap, visited);
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
