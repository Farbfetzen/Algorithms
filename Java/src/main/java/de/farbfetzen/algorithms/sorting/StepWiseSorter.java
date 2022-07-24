package de.farbfetzen.algorithms.sorting;

import java.util.Set;

public interface StepWiseSorter {

    void step();

    int[] getArray();

    boolean isFinished();

    Set<Integer> getComparisons();

    Set<Integer> getHighlights();

}
