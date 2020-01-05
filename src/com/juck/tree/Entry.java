package com.juck.tree;

import com.juck.tree.impl.AVLTree;
import com.juck.tree.impl.BSTree;

public class Entry {
    public static void main(String[] args) {
//        bstree();

        ITree<Integer> avlTree = new AVLTree<>();

        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        avlTree.add(4);

        System.out.println(avlTree.toString());
    }

    private static void bstree() {
        ITree<Integer> bstree = new BSTree<>();

        bstree.add(4);
        bstree.add(2);
        bstree.add(3);
        bstree.add(1);
        bstree.add(6);
        bstree.add(5);
        bstree.add(7);

        bstree.remove(4);

        System.out.println(bstree);
    }
}
