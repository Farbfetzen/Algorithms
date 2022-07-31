package de.farbfetzen.algorithms.sorting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
    protected final List<SortStep> steps = new ArrayList<>();
    private final Iterator<SortStep> stepIterator;

    /**
     * One step that was performed and recorded during the sorting of the array.
     * The visualizations work by recording and then replaying these steps.
     *
     * @param swap        the swapped indices or null if no swap happened
     * @param comparisons the compared indices or null if nothing was compared
     * @param highlights  the highlighted indices or null if nothing was highlighted
     */
    @SuppressWarnings("squid:S6218")
    private record SortStep(int[] swap, Set<Integer> comparisons, Set<Integer> highlights) {}

    StepWiseSorter(final int[] array) {
        this.array = array;
        sortAndRecord(array.clone());
        stepIterator = steps.iterator();
    }

    protected abstract void sortAndRecord(final int[] array);

    void step() {
        if (stepIterator.hasNext()) {
            final var step = stepIterator.next();
            if (step.swap() != null) {
                SortingUtils.swap(array, step.swap()[0], step.swap()[1]);
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

    protected static class SortStepBuilder {

        private int[] swap;
        private Set<Integer> comparisons;
        private Set<Integer> highlights;

        /**
         * Swap the values at the given indices and record that step.
         */
        SortStepBuilder swap(final int[] array, final int i, final int j) {
            SortingUtils.swap(array, i, j);
            swap = new int[]{i, j};
            return this;
        }

        /**
         * Record when value are compared, not indices.
         */
        SortStepBuilder compare(final int... indices) {
            comparisons = new HashSet<>();
            for (final var i : indices) {
                comparisons.add(i);
            }
            return this;
        }

        SortStepBuilder highlight(final int... indices) {
            highlights = new HashSet<>();
            for (final var i : indices) {
                highlights.add(i);
            }
            return this;
        }

        SortStepBuilder clearComparisons() {
            comparisons = Set.of();
            return this;
        }

        SortStepBuilder clearHighlights() {
            highlights = Set.of();
            return this;
        }

        SortStep build() {
            return new SortStep(swap, comparisons, highlights);
        }

    }

}
