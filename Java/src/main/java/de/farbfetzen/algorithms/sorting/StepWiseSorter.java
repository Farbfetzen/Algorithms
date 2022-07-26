package de.farbfetzen.algorithms.sorting;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

abstract class StepWiseSorter {

    @Getter
    protected final int[] array;
    @Getter
    protected boolean finished = false;
    @Getter
    protected Set<Integer> highlights = new HashSet<>();
    @Getter
    protected Set<Integer> comparisons = new HashSet<>();

    StepWiseSorter(final int[] array) {
        this.array = array;
    }

    abstract void step();

}
