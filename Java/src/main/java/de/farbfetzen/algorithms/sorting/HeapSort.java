package de.farbfetzen.algorithms.sorting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

@Slf4j
public class HeapSort implements StepWiseSorter {

    private static final Set<Integer> CLEAR = Set.of();

    @Getter
    private final int[] array;
    @Getter
    private Set<Integer> highlights = new HashSet<>();
    @Getter
    private Set<Integer> comparisons = new HashSet<>();
    @Getter
    private boolean finished = false;
    private final List<Step> steps = new ArrayList<>();
    private final Iterator<Step> stepIterator;

    /**
     * One step that was performed and recorded during the sorting of the array.
     * The visualization works by replaying these steps.
     * @param swap the swapped indices or null if no swap happened
     * @param comparisons the compared indices or null if nothing was compared
     * @param highlights the highlighted indices or null if nothing was highlighted
     */
    @SuppressWarnings("squid:S6218")
    private record Step(int[] swap, Set<Integer> comparisons, Set<Integer> highlights) {}

    public HeapSort(final int[] array) {
        this.array = array;
        sortAndRecord(array.clone());
        stepIterator = steps.iterator();
    }

    public void step() {
        if (stepIterator.hasNext()) {
            final var step = stepIterator.next();
            if (step.swap != null) {
                swap(array, step.swap[0], step.swap[1]);
            }
            if (step.comparisons != null) {
                comparisons = step.comparisons;
            }
            if (step.highlights != null) {
                highlights = step.highlights;
            }
        } else {
            finished = true;
        }
    }

    private void sortAndRecord(final int[] array) {
        buildHeapAndRecord(array);
        for (int i = array.length - 1; i >= 1; i--) {
            steps.add(new Step(new int[]{0, i}, null, Set.of(i)));
            swap(array, 0, i);
            heapifyAndRecord(array, 0, i);
        }
    }

    private void buildHeapAndRecord(final int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            steps.add(new Step(null, CLEAR, Set.of(i)));
            heapifyAndRecord(array, i, array.length);
        }
    }

    private void heapifyAndRecord(final int[] array, final int i, final int upperBound) {
        var largest = i;
        final var left = i * 2 + 1;
        final var right = i * 2 + 2;
        if (left < upperBound) {
            steps.add(new Step(null, Set.of(largest, left), null));
            if (array[largest] < array[left]) {
                largest = left;
            }
        }
        if (right < upperBound) {
            steps.add(new Step(null, Set.of(largest, right), null));
            if (array[largest] < array[right]) {
                largest = right;
            }
        }
        if (largest != i) {
            steps.add(new Step(new int[]{largest, i}, CLEAR, null));
            swap(array, largest, i);
            heapifyAndRecord(array, largest, upperBound);
        }
    }

    /**
     * Sort an array in place using heap sort.
     *
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
