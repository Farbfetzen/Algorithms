package de.farbfetzen.algorithms.sorting;

import java.util.Set;

class InsertionSort extends StepWiseSorter {

    private int i = 0;
    private int j = i - 1;

    InsertionSort(final int[] array) {
        super(array, false);
    }

    private void checkIfFinished() {
        finished = i >= array.length;
    }

    @Override
    void step() {
        if (finished) {
            return;
        }
        var k = j + 1;
        if (j >= 0 && array[j] > array[k]) {
            SortingUtils.swap(array, j, k);
            j--;
            k--;
        } else {
            i++;
            j = i - 1;
            k = i;
        }
        comparisons.clear();
        comparisons.add(j);
        comparisons.add(k);
        highlights = Set.of(i);
        checkIfFinished();
    }

    /**
     * Sort an array in place using insertion sort.
     *
     * @param array the array to sort
     */
    static void sort(final int[] array) {
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
