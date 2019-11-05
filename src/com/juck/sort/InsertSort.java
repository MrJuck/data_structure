package com.juck.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        Integer[] origin = {1, 2, 3, 4};
        insertSort(origin);
        Arrays.asList(origin).forEach(e -> System.out.println(e));
    }

    private static void insertSort(Integer[] origin) {
        if (origin == null || origin.length < 1) {
            return ;
        }

        for (int i = 1; i < origin.length; i++) {
            int toBeSorted = origin[i];
            int inOrder = i - 1;

            while (inOrder >= 0 && toBeSorted > origin[inOrder]) {
                origin[inOrder+1] = origin[inOrder];
                inOrder--;
            }

            origin[++inOrder] = toBeSorted;
        }
    }
}
