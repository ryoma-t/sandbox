package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import lombok.Data;

@Data
public class Node<T> {

    private T value;

    public Node(T value) {
        this.value = value;
    }

}
