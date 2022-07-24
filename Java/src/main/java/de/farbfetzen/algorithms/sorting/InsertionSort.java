package de.farbfetzen.algorithms.sorting;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

public class InsertionSort implements StepWiseSorter {

    @Getter
    private final int[] array;
    private int i = 0;
    private int j = i - 1;
    @Getter
    private final Set<Integer> comparisons = new HashSet<>();
    @Getter
    private boolean finished = false;

    public InsertionSort(final int[] array) {
        this.array = array;
    }

    private void checkIfFinished() {
        finished = i >= array.length;
    }

    @Override
    public Set<Integer> getHighlights() {
        return Set.of(i);
    }

    public void step() {
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
        checkIfFinished();
    }

    /**
     * Sort an array in place using insertion sort.
     *
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
