package de.farbfetzen.algorithms.sorting;

import lombok.extern.slf4j.Slf4j;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

@Slf4j
class HeapSort extends StepWiseSorter {

    HeapSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sortAndRecord(final int[] array) {
        buildHeapAndRecord(array);
        for (int i = array.length - 1; i >= 1; i--) {
            steps.add(new SortStepBuilder().swap(array, 0, i).highlight(i).build());
            heapifyAndRecord(array, 0, i);
        }
    }

    private void buildHeapAndRecord(final int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            steps.add(new SortStepBuilder().highlight(i).clearComparisons().build());
            heapifyAndRecord(array, i, array.length);
        }
    }

    private void heapifyAndRecord(final int[] array, final int i, final int upperBound) {
        var largest = i;
        final var left = i * 2 + 1;
        final var right = i * 2 + 2;
        if (left < upperBound) {
            steps.add(new SortStepBuilder().compare(largest, left).build());
            if (array[largest] < array[left]) {
                largest = left;
            }
        }
        if (right < upperBound) {
            steps.add(new SortStepBuilder().compare(largest, right).build());
            if (array[largest] < array[right]) {
                largest = right;
            }
        }
        if (largest != i) {
            steps.add(new SortStepBuilder().swap(array, largest, i).clearComparisons().build());
            heapifyAndRecord(array, largest, upperBound);
        }
    }

    /**
     * Sort an array in place using heap sort.
     *
     * @param array the array to sort
     */
    static void sort(final int[] array) {
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
