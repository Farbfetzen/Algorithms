package de.farbfetzen.algorithms.sorting;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;


class QuickSort extends StepWiseSorter {

    QuickSort(final int[] array) {
        super(array, true);
    }

    @Override
    protected void sortAndRecord(final int[] array) {
        quickSortAndRecord(array, 0, array.length - 1);
    }

    private void quickSortAndRecord(final int[] array, final int leftIndex, final int rightIndex) {
        if (leftIndex < rightIndex) {
            final var pivotIndex = partitionAndRecord(array, leftIndex, rightIndex);
            quickSortAndRecord(array, leftIndex, pivotIndex - 1);
            quickSortAndRecord(array, pivotIndex + 1, rightIndex);
        }
    }

    private int partitionAndRecord(final int[] array, final int leftIndex, final int rightIndex) {
        // If there are only two values then sort them immediately.
        if (rightIndex - leftIndex < 2) {
            final var leftValue = array[leftIndex];
            final var rightValue = array[rightIndex];
            steps.add(new SortStepBuilder().compare(leftIndex, rightIndex).build());
            if (leftValue > rightValue ) {
                steps.add(new SortStepBuilder().swap(array, leftIndex, rightIndex).build());
            }
            return leftIndex;
        }
        final var pivotIndex = (leftIndex + rightIndex) / 2;
        steps.add(new SortStepBuilder().highlight(pivotIndex).clearComparisons().build());
        final var pivotValue = array[pivotIndex];
        steps.add(new SortStepBuilder().swap(array, pivotIndex, rightIndex).highlight(rightIndex).build());
        var storeIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            steps.add(new SortStepBuilder().compare(i, rightIndex).build());
            if (array[i] <= pivotValue) {
                steps.add(new SortStepBuilder().swap(array, i, storeIndex).highlight(storeIndex + 1).build());
                storeIndex++;
            }
        }
        steps.add(new SortStepBuilder().swap(array, rightIndex, storeIndex).build());
        return storeIndex;
    }

    /**
     * Sort an array in place using quick sort.
     *
     * @param array the array to sort
     */
    static void sort(final int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(final int[] array, final int leftIndex, final int rightIndex) {
        if (leftIndex < rightIndex) {
            final var pivotIndex = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, rightIndex);
        }
    }

    private static int partition(final int[] array, final int leftIndex, final int rightIndex) {
        final var pivotIndex = rightIndex - leftIndex > 2? (rightIndex + leftIndex) / 2 : leftIndex;
        final var pivotValue = array[pivotIndex];
        swap(array, pivotIndex, rightIndex);
        var storeIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            if (array[i] <= pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, rightIndex, storeIndex);
        return storeIndex;
    }

}
