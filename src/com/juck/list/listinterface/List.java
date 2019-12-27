package com.juck.list.listinterface;

public interface List<E> {
    int size();

    boolean isEmpty();

    boolean add(E e);

    E remove(E e);

    boolean contains(E e);

    void print();
}