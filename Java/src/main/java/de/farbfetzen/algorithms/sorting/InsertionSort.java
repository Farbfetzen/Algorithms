package de.farbfetzen.algorithms.sorting;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InsertionSort {

    /**
     * Sort an array in place using insertion sort.
     * @param array the array to sort
     */
    public static void sort(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            var j = i - 1;
            final var current = array[i];
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

}
