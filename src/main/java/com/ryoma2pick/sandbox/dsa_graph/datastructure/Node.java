package com.ryoma2pick.sandbox.dsa_graph.datastructure;

import lombok.Data;

@Data
public class Node<T> {

    private T value;

    public Node(T value) {
        this.value = value;
    }

}
