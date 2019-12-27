package com.juck.list;

import com.juck.list.listinterface.List;
import com.juck.list.listinterface.impl.SinglyLinkedList;

public class Entry {
    public static void main(String[] args) {
        List<Integer> list = new SinglyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.err.println(list.remove(13));

        list.print();
    }
}
