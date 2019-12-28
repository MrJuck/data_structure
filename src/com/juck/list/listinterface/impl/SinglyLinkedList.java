package com.juck.list.listinterface.impl;

import com.juck.list.listinterface.List;

import java.util.Objects;

public class SinglyLinkedList<E> implements List<E> {
    private Node<E> head;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e, null);

        if (Objects.isNull(head)) {
            head = newNode;
            size++;
            return true;
        }

        Node<E> current = head;
        while (Objects.nonNull(current.next)) {
            current = current.next;
        }
        size++;
        current.next = newNode;
        return true;
    }

    @Override
    public E remove(E e) {
        E value = null;
        checkBoud();

        if (Objects.equals(head.item, e)) {
            value = head.item;
            head = head.next;
            size--;
            return value;
        }

        Node<E> current = head;
        while (Objects.nonNull(current.next)) {
            if (Objects.equals(current.next.item, e)) {
                value = current.next.item;
                current.next = current.next.next;

                size--;
                return value;
            }

            current = current.next;
        }

        return value;
    }

    @Override
    public boolean contains(E e) {
        checkBoud();

        if (Objects.equals(head.item, e)) {
            return true;
        }

        Node<E> current = head;
        while (Objects.nonNull(current.next)) {
            if (Objects.equals(current.next.item, e)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public void print() {
        Node<E> current = head;
        while (Objects.nonNull(current)) {
            System.err.printf("%d -> ", current.item);
            current = current.next;
        }
        System.err.println("NULL");
    }

    private void checkBoud() {
        if (size == 0 || Objects.isNull(head)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
