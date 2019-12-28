package com.juck.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] source = generateArray(10);
        System.out.println(Arrays.toString(source));

        quickSort(source, 0, source.length - 1);

        System.out.println(Arrays.toString(source));
    }

    private static void quickSort(int[] source, int left, int right) {
        if (left < right) {
            int pivot = partition(source, left, right);

            quickSort(source, left, pivot - 1);

            quickSort(source, pivot + 1, right);
        }
    }

    private static int partition(int[] source, int left, int right) {
        int pivot = source[left];

        while (left < right) {
            while (left < right && pivot <= source[right]) {
                right--;
            }
            source[left] = source[right];

            while (left < right && pivot >= source[left]) {
                left++;
            }
            source[right] = source[left];
        }
        source[left] = pivot;
        return left;
    }


    private static int[] generateArray(int length) {
        int[] source = new int[length];

        for (int i = 0; i < length; i++) {
            source[i] = (int) (Math.random() * 1000);
        }

        return source;
    }
}
