package com.juck.list.listinterface.impl;

import com.juck.list.listinterface.List;

import java.util.Objects;

public class singlyCircularLinkedList<E> implements List<E> {
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
        Node<E> newNode = new Node<>(e, head); // 在链表末端添加， 所以next一定指向head。

        if (Objects.isNull(head)) { // 创建头节点。
            head = newNode;
            head.next = head;
            size++;
            return true;
        }

        /**
         * 遍历链表找到最后一个节点，
         * 因为是环形的，所以如果我的下一个是头节点，那么我就是最后一个节点了
         */
        Node<E> current = head;
        while (!Objects.equals(current.next, head)) {
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

        if (Objects.equals(head.item, e)) { // 删除头节点
            value = head.item;

            /**
             * 此时链表只有头节点
             */
            if (Objects.equals(head, head.next)) {
                head = null;
                size--;
                return value;
            }

            Node<E> current = head;
            while (!Objects.equals(current.next, head)) {
                current = current.next;
            }

            current.next = head.next;
            head = head.next;

            size--;
            return value;
        }

        Node<E> current = head;
        while (!Objects.equals(current.next, head)) {
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
        if (size == 0 || Objects.isNull(head)) {
            return false;
        }

        if (Objects.equals(head.item, e)) {
            return true;
        }

        Node<E> current = head;
        while (!Objects.equals(current.next, head)) {
            if (Objects.equals(current.next.item, e)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public void print() {
        if (size == 0 || Objects.isNull(head)) {
            System.err.printf("%s\r\n", "null");
            return;
        }
        Node<E> current = head;
        while (!Objects.equals(current.next, head)) {
            System.err.printf("%s -> ", current.item.toString());
            current = current.next;
        }

        System.err.printf("%s -> ", current.item.toString());
        current = current.next;
        System.err.printf("%s\r\n", current.item.toString());
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
