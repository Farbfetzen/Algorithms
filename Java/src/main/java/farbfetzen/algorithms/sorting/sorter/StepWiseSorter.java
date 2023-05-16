package farbfetzen.algorithms.sorting.sorter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import farbfetzen.algorithms.sorting.SortingUtils;

public abstract class StepWiseSorter {

    protected final int[] array;
    protected final int[] replayArray;
    protected boolean finished = false;
    protected Set<Integer> highlights = new HashSet<>();
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

    protected StepWiseSorter(final int[] array) {
        this.array = array;
        this.replayArray = array.clone();
        sort();
        stepIterator = steps.iterator();
    }

    protected abstract void sort();

    public void step() {
        if (stepIterator.hasNext()) {
            final var step = stepIterator.next();
            if (step.swap() != null) {
                SortingUtils.swap(replayArray, step.swap()[0], step.swap()[1]);
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

    public int[] getReplayArray() {
        return replayArray;
    }

    public boolean isFinished() {
        return finished;
    }

    public Set<Integer> getHighlights() {
        return highlights;
    }

    public Set<Integer> getComparisons() {
        return comparisons;
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
