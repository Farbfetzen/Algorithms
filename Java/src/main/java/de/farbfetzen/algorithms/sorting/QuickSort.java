package de.farbfetzen.algorithms.sorting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

class QuickSort extends StepWiseSorter {

    private final List<SortStep> steps = new ArrayList<>();
    private final Iterator<SortStep> stepIterator;

    QuickSort(final int[] array) {
        super(array);
        sortAndRecord(array.clone());
        stepIterator = steps.iterator();
    }

    private void sortAndRecord(final int[] array) {
        quickSortAndRecord(array, 0, array.length - 1);
    }

    private void quickSortAndRecord(final int[] array, final int leftIndex, final int rightIndex) {
        if (leftIndex < rightIndex) {
            steps.add(new SortStep(null, Set.of(leftIndex, rightIndex), null));
            final var pivotIndex = partitionAndRecord(array, leftIndex, rightIndex);
            quickSortAndRecord(array, leftIndex, pivotIndex - 1);
            quickSortAndRecord(array, pivotIndex + 1, rightIndex);
        } else {
            steps.add(new SortStep(null, Set.of(leftIndex), null));
        }
    }

    private int partitionAndRecord(final int[] array, final int leftIndex, final int rightIndex) {
        final var pivotIndex = rightIndex - leftIndex > 2? (rightIndex + leftIndex) / 2 : leftIndex;
        steps.add(new SortStep(null, null, Set.of(pivotIndex)));
        final var pivotValue = array[pivotIndex];
        swap(array, pivotIndex, rightIndex);
        steps.add(new SortStep(new int[]{pivotIndex, rightIndex}, null, Set.of(rightIndex)));
        var storeIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            steps.add(new SortStep(null, Set.of(i, rightIndex), null));
            if (array[i] <= pivotValue) {
                steps.add(new SortStep(new int[]{i, storeIndex}, null, Set.of(storeIndex + 1)));
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, rightIndex, storeIndex);
        steps.add(new SortStep(new int[]{rightIndex, storeIndex}, null, null));
        return storeIndex;
    }

    @Override
    void step() {
        if (stepIterator.hasNext()) {
            final var step = stepIterator.next();
            if (step.swap() != null) {
                swap(array, step.swap()[0], step.swap()[1]);
            }
            if (step.comparisons() != null) {
                comparisons = step.comparisons();
            }
            if (step.highlights() != null) {
                highlights = step.highlights();
            }
        } else {
            finished = true;
        }
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
