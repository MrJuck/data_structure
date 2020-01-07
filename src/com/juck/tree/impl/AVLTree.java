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

        Node<E> newNode = new Node<>(e);
        if (Objects.isNull(root)) {
            root = newNode;
        } else {
            root.add(newNode);
        }

        return true;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public E search(E e) {
        if (Objects.isNull(e)) {
            throw new RuntimeException("null element is not allowed!");
        }

        if (Objects.isNull(root)) {
            return null;
        }

        Node<E> result = root.search(e);
        return Objects.isNull(result) ? null : result.item;
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

        private void add(Node<E> node) {
            if (this.item.compareTo(node.item) > 0) {
                if (Objects.isNull(this.left)) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else if (this.item.compareTo(node.item) < 0) {
                if (Objects.isNull(this.right)) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            } else {
                this.item = node.item;
            }

            // adjust tree to meet requirements of AVL Tree
            this.avlTreeify();
        }

        private void avlTreeify() {
            // calculate the balance factor of current node. the height differences between right and left sub-trees.
            int balanceFactor = this.rightHeight() - this.leftHeight();

            // if balance factor is grater than 1, it means we need
            if (balanceFactor > 1) {
                if (this.right.leftHeight() > this.right.rightHeight()) {
                    this.right.rightRotate();
                }
                this.leftRotate();

                return ;
            }

            if (balanceFactor < 1) {
                if (this.left.rightHeight() > this.left.leftHeight()) {
                    this.left.leftRotate();
                }

                this.rightRotate();
            }
        }

        private Node<E> search(E e) {
            if (this.item.equals(e)) {
                return this;
            }

            if (this.item.compareTo(e) > 0 && Objects.nonNull(this.left)) {
                return this.left.search(e);
            }

            if (this.item.compareTo(e) < 0 && Objects.nonNull(this.right)) {
                return this.right.search(e);
            }

            return null;
        }

        private int leftHeight() {
            return Objects.isNull(this.left) ? 0 : this.left.height();
        }

        private int rightHeight() {
            return Objects.isNull(this.right) ? 0 : this.right.height();
        }

        private int height() {
            int leftHeight = Objects.isNull(this.left) ? 0 : this.left.height();
            int rightHeight = Objects.isNull(this.right) ? 0 : this.right.height();

            return Math.max(leftHeight, rightHeight) + 1;
        }

        private void leftRotate() {
            Node<E> newNode = new Node<>(this.item);
            newNode.right = this.right.left;
            newNode.left = this.left;
            this.item = this.right.item;
            this.right = this.right.right;
            this.left = newNode;
        }

        private void rightRotate() {
            Node<E> newNode = new Node<>(this.item);
            newNode.left = this.left.right;
            newNode.right = this.right;
            this.item = this.left.item;
            this.left = this.left.left;
            this.right = newNode;
        }
    }
}
