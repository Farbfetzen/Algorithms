package de.farbfetzen.algorithms.sorting;

import lombok.experimental.UtilityClass;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

@UtilityClass
public class SelectionSort {

    /**
     * Sort an array in place using selection sort.
     * @param array the array to sort
     */
    public static void sort(final int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, i, findIndexOfMaxValue(array, i));
        }
    }

    private static int findIndexOfMaxValue(final int[] array, final int upperBound) {
        var indexOfMaxValue = 0;
        for (int i = 1; i <= upperBound; i++) {
            if (array[i] > array[indexOfMaxValue]) {
                indexOfMaxValue = i;
            }
        }
        return indexOfMaxValue;
    }

}
