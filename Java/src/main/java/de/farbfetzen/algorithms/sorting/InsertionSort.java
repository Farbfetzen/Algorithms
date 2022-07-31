package de.farbfetzen.algorithms.sorting;

class InsertionSort extends StepWiseSorter {

    InsertionSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sortAndRecord(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            var j = i - 1;
            final var currentValue = array[i];
            steps.add(new SortStepBuilder().highlight(i).clearComparisons().build());
            for (; j >= 0; j--) {
                steps.add(new SortStepBuilder().compare(j, j + 1).highlight(i).build());
                if (array[j] > currentValue) {
                    steps.add(new SortStepBuilder().swap(array, j, j + 1).build());
                } else {
                    break;
                }
            }
        }
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
            for (; j >= 0 && array[j] > current; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = current;
        }
    }

}
