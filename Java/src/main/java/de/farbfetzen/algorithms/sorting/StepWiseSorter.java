package de.farbfetzen.algorithms.sorting;

import java.util.Set;

public interface StepWiseSorter {

    interface Constructor {
        StepWiseSorter construct(int[] array);
    }

    void step();

    int[] getArray();

    boolean isFinished();

    Set<Integer> getComparisons();

    Set<Integer> getHighlights();

}
