package de.farbfetzen.algorithms.sorting;

import lombok.experimental.UtilityClass;

/**
 * If a method is used in more than one sorting algorithm it gets put in here.
 */
@UtilityClass
public class SortingUtils {

    static void swap(final int[] array, final int i, final int j) {
        if (i != j) {
            final var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
