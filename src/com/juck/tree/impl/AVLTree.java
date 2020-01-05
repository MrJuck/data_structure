package com.juck.tree.impl;

import com.juck.tree.ITree;

import java.util.Objects;

public class AVLTree<E extends Comparable<E>> implements ITree<E> {
    private Node<E> root;

    @Override
    public boolean add(E e) {
        if (Objects.isNull(e)) {
            throw new RuntimeException("Element to be inserted must not be null");
        }

        root = doAdd(root, new Node<>(e));
        System.out.println(height(root));
        return true;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public boolean search(E e) {
        return false;
    }

    private Node<E> doAdd(Node<E> target, Node<E> newNode) {
        if (Objects.isNull(target)) { // insert new element here
            return newNode;
        }

        /**
         * insert node recursively
         */
        if (target.item.compareTo(newNode.item) > 0) {
            target.left = doAdd(target.left, newNode);
        } else if (target.item.compareTo(newNode.item) < 0) {
            target.right = doAdd(target.right, newNode);
        } else {
            // same value is not allowed, so new value will replace the former one.
            target.item = newNode.item;
        }

        return avlify(target);
    }

    private Node<E> avlify(Node<E> target) {
        int leftHeight = height(target.left);
        int rightHeight = height(target.right);

        return target;
    }

    private int height(Node<E> root) {
        if (Objects.isNull(root)) {
            return 0;
        }

        int leftHeight = Objects.isNull(root.left) ? 0 : height(root.left);
        int rightHeight = Objects.isNull(root.right) ? 0 : height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public String toString() {
        return "AVLTree{" +
                "root=" + root +
                '}';
    }

    private static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
        Node<E> right;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
