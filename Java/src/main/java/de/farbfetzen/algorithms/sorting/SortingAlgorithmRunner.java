package de.farbfetzen.algorithms.sorting;

import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SortingAlgorithmRunner {

    public static final Random random = new Random();
    private static final Map<String, Consumer<int[]>> SORTING_ALGORITHMS = Map.of(
            "insertion sort", InsertionSort::sort,
            "selection sort", SelectionSort::sort,
            "heap sort", HeapSort::sort
    );

    public static boolean isSortingAlgorithm(final String algorithmName) {
        return SORTING_ALGORITHMS.containsKey(algorithmName);
    }

    public static void run(final String algorithmName, final String[] args) {
        // TODO: Proper argument parsing.
        final var elements = random.ints(10, 0, 100).toArray();
        logger.info("Original: {}", elements);
        final var algo = SORTING_ALGORITHMS.get(algorithmName);
        algo.accept(elements);
        logger.info("Sorted:   {}", elements);
    }

}
