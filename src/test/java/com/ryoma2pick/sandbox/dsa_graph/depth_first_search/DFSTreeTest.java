package com.ryoma2pick.sandbox.dsa_graph.depth_first_search;

import com.ryoma2pick.sandbox.dsa_graph.datastructure.LinkedNode;
import com.ryoma2pick.sandbox.dsa_graph.depth_first_search.DFSTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DFSTreeTest {

    private LinkedNode<Character> root;

    @Test
    void dfsRecursion() {
        DFSTree.dfsRecursion(root);
    }

    @Test
    void dfsStack() {
        DFSTree.dfsStack(root);
    }

    @BeforeEach
    private void setup() {
    /*
         A
        / \
       B   G
      /|\
     C D F
       |
       E
    */
        LinkedNode<Character> nodeA = new LinkedNode<>('A');
        LinkedNode<Character> nodeB = new LinkedNode<>('B');
        LinkedNode<Character> nodeC = new LinkedNode<>('C');
        LinkedNode<Character> nodeD = new LinkedNode<>('D');
        LinkedNode<Character> nodeE = new LinkedNode<>('E');
        LinkedNode<Character> nodeF = new LinkedNode<>('F');
        LinkedNode<Character> nodeG = new LinkedNode<>('G');

        nodeA.addChild(nodeB);
        nodeA.addChild(nodeG);
        nodeB.addChild(nodeC);
        nodeB.addChild(nodeD);
        nodeB.addChild(nodeF);
        nodeD.addChild(nodeE);

        root = nodeA;
    }

}