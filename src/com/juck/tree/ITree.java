package com.juck.tree;

public interface ITree<E extends Comparable<E>> {
    boolean add(E e);

    boolean remove(E e);

    E search(E e);
}
