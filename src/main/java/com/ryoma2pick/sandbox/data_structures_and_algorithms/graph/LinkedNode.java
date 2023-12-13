package com.ryoma2pick.sandbox.data_structures_and_algorithms.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class LinkedNode<T> extends Node<T> {

    private List<LinkedNode<T>> children;

    public LinkedNode(T value) {
        super(value);
        children = new LinkedList<>();
    }

    public void addChild(LinkedNode<T> node) {
        children.add(node);
    }

}
