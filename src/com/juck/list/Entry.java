package com.juck.list;

import com.juck.list.listinterface.List;
import com.juck.list.listinterface.impl.SinglyLinkedList;
import com.juck.list.listinterface.impl.singlyCircularLinkedList;

public class Entry {
    public static void main(String[] args) {
        singlyLinkedList();
        singlyCircularLinkedList();
    }

    private static void singlyCircularLinkedList() {
        System.err.println("begin test: singly circular linked list");
        List<Integer> list = new singlyCircularLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.print();
        System.err.println(list.contains(3));
        System.err.println(list.remove(3));
        System.err.println(list.contains(3));
        list.print();

        System.err.println("end test: singly circular linked list");
        System.err.println("nJosephu: ");
        ((singlyCircularLinkedList<Integer>) list).nJosephus(41, 1, 3, 2);
    }


    private static void singlyLinkedList() {
        System.err.println("begin test: singly linked list");
        List<Integer> list = new SinglyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.err.println(list.remove(13));
        System.err.println(list.remove(1));
        System.err.println(list.contains(1));
        System.err.println(list.contains(2));
        list.print();
        System.err.println("end test: singly linked list");
    }
}
