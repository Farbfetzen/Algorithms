package de.farbfetzen.algorithms.sorting;

import java.util.Set;

/**
 * One step that was performed and recorded during the sorting of the array.
 * The visualization works by replaying these steps.
 *
 * @param swap        the swapped indices or null if no swap happened
 * @param comparisons the compared indices or null if nothing was compared
 * @param highlights  the highlighted indices or null if nothing was highlighted
 */
@SuppressWarnings("squid:S6218")
public record SortStep(int[] swap, Set<Integer> comparisons, Set<Integer> highlights) {}
