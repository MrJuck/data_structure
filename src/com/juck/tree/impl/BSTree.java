package com.juck.tree.impl;

import com.juck.tree.ITree;

import java.util.Objects;

public class BSTree<E extends Comparable<E>> implements ITree<E> {
    private Node<E> root;

    @Override
    public boolean add(E e) {
        root = doAdd(root, e);
        return true;
    }

    private Node<E> doAdd(Node<E> parent, E e) {
        Node<E> newNode = new Node<>(e);
        if (Objects.isNull(parent)) {
            return newNode;
        }

        if (parent.item.compareTo(e) > 0) {
            parent.left = doAdd(parent.left, e);
        } else {
            parent.right = doAdd(parent.right, e);
        }

        return parent;
    }

    @Override
    public boolean remove(E e) {
        if (search(e)) {
            return false;
        }

        root = doRemove(root, e); // delete node recursively

        return true;
    }

    private Node<E> doRemove(Node<E> current, E e) {
        if (current.item.equals(e)) { // node to be deleted
            return doDelete(current);
        }

        if (current.item.compareTo(e) > 0) { // target node is on the left
            current.left = doRemove(current.left, e);
        } else { // target node is on the right
            current.right = doRemove(current.right, e);
        }

        return current;
    }

    private Node<E> doDelete(Node<E> current) {
        /**
        * node to be deleted only has left sub-tree or is leaf node
        */
        if (current.right == null) {
            return current.left;
        }

        /**
         * node to be deleted only has right sub-tree or is leaf node
         */
        if (current.left == null) {
            return current.right;
        }

        /**
         * node to be deleted(delNode) has both left and right sub-tree,
         * find successor node and copy content to delNode,
         * then, delete successor from delNode's right sub-tree recursively.
         */
        Node<E> successor = findSuccessor(current); // find successor node
        current.item = successor.item; // copy content
        current.right = doRemove(current.right, successor.item); // delete successor node from right sub-tree recursively

        return current;
    }

    private Node<E> findSuccessor(Node<E> root) {
        root = root.right;
        while (Objects.nonNull(root.left)) {
            root = root.left;
        }

        return root;
    }

    @Override
    public String toString() {
        return "BSTree{" +
                "root=" + root +
                '}';
    }

    @Override
    public boolean search(E e) {
        return Objects.isNull(doSearch(root, e));
    }

    private Node<E> doSearch(Node<E> parent, E e) {
        if (Objects.isNull(parent)) {
            return null;
        }

        if (parent.item.compareTo(e) > 0) {
            return doSearch(parent.left, e);
        }

        if (parent.item.compareTo(e) < 0) {
            return doSearch(parent.right, e);
        }

        return parent;
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
