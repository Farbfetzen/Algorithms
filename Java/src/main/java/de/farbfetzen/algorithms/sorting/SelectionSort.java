package de.farbfetzen.algorithms.sorting;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

class SelectionSort extends StepWiseSorter {

    SelectionSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sortAndRecord(final int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            steps.add(new SortStepBuilder().highlight(i + 1).clearComparisons().build());
            final var indexOfMaxValue = findIndexOfMaxValueAndRecord(array, i);
            steps.add(new SortStepBuilder().swap(array, i, indexOfMaxValue).build());
        }
    }

    private int findIndexOfMaxValueAndRecord(final int[] array, final int upperBound) {
        var indexOfMaxValue = 0;
        for (int i = 1; i <= upperBound; i++) {
            steps.add(new SortStepBuilder().compare(i, indexOfMaxValue).build());
            if (array[i] > array[indexOfMaxValue]) {
                indexOfMaxValue = i;
            }
        }
        return indexOfMaxValue;
    }

    /**
     * Sort an array in place using selection sort.
     *
     * @param array the array to sort
     */
    static void sort(final int[] array) {
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
