package com.ryoma2pick.sandbox.dsa.graph.datastructure;

import lombok.Data;

@Data
public class Node<T> {

    private T value;

    public Node(T value) {
        this.value = value;
    }

}
