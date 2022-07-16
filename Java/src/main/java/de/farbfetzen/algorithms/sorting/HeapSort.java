package de.farbfetzen.algorithms.sorting;

import lombok.experimental.UtilityClass;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

@UtilityClass
public class HeapSort {

    /**
     * Sort an array in place using heap sort.
     * @param array the array to sort
     */
    public static void sort(final int[] array) {
        buildHeap(array);
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            heapify(array, 0, i);
        }
    }

    private static void buildHeap(final int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i, array.length);
        }
    }

    private static void heapify(final int[] array, final int i, final int upperBound) {
        var largest = i;
        final var left = i * 2 + 1;
        final var right = i * 2 + 2;
        if (left < upperBound && array[largest] < array[left]) {
            largest = left;
        }
        if (right < upperBound && array[largest] < array[right]) {
            largest = right;
        }
        if (largest != i) {
            swap(array, largest, i);
            heapify(array, largest, upperBound);
        }
    }

}
