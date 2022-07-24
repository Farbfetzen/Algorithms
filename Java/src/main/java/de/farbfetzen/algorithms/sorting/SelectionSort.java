package de.farbfetzen.algorithms.sorting;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

import static de.farbfetzen.algorithms.sorting.SortingUtils.swap;

public class SelectionSort implements StepWiseSorter {

    @Getter
    private final int[] array;
    @Getter
    private boolean finished = false;
    @Getter
    private final Set<Integer> comparisons = new HashSet<>();
    private int i;
    private int maxValueCurrentIndex = 0;
    private int maxValueSearchIndex = 0;

    public SelectionSort(final int[] array) {
        this.array = array;
        i = array.length - 1;
        checkIfFinished();
    }

    @Override
    public Set<Integer> getHighlights() {
        return Set.of(i);
    }

    private void checkIfFinished() {
        finished = i <= 0;
    }

    @Override
    public void step() {
        if (finished) {
            return;
        }
        if (maxValueSearchIndex < i) {
            maxValueSearchIndex++;
            if (array[maxValueSearchIndex] > array[maxValueCurrentIndex]) {
                maxValueCurrentIndex = maxValueSearchIndex;
            }
        } else {
            swap(array, i, maxValueCurrentIndex);
            i--;
            maxValueCurrentIndex = 0;
            maxValueSearchIndex = 0;
        }
        comparisons.clear();
        comparisons.add(maxValueCurrentIndex);
        comparisons.add(maxValueSearchIndex);
        checkIfFinished();
    }

    /**
     * Sort an array in place using selection sort.
     *
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
