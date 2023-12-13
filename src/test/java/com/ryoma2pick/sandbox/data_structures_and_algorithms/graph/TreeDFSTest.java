package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import com.ryoma2pick.sandbox.data_structures_and_algorithms.graph.TreeDFS;
import org.junit.jupiter.api.Test;

class TreeDFSTest {

    private LinkedNode<Character> root;

    @Test
    void dfsRecursion() {
        setup();
        TreeDFS dfs=new TreeDFS();
        dfs.dfsRecursion(root);
    }

    @Test
    void dfsStack() {
        setup();
        TreeDFS dfs=new TreeDFS();
        dfs.dfsStack(root);
    }

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