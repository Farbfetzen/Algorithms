package de.farbfetzen.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import processing.core.PApplet;

import de.farbfetzen.algorithms.AlgorithmRunner;

@Slf4j
public class SortingAlgorithmRunner implements AlgorithmRunner {

    private static final Random random = new Random();

    @RequiredArgsConstructor
    enum SortAlgo {
        INSERTION_SORT("insertion sort", InsertionSort::sort, InsertionSort.class.getName()),
        SELECTION_SORT("selection sort", SelectionSort::sort, null),
        HEAP_SORT("heap sort", HeapSort::sort, null);

        final String name;
        final Consumer<int[]> sortMethod;
        final String className;

        static SortAlgo getByName(final String name) {
            return Arrays.stream(values()).filter(v -> v.name.equals(name)).findFirst().orElseThrow();
        }
    }

    private static final Set<String> algorithmNames = Arrays
            .stream(SortAlgo.values())
            .map(v -> v.name)
            .collect(Collectors.toSet());

    @Override
    public boolean hasAlgorithm(final String algorithmName) {
        return algorithmNames.contains(algorithmName);
    }

    public void run(final String algorithmName, final String[] args) {
        // TODO: Proper argument parsing.
        logger.info("Running {}", algorithmName);
        final var algo = SortAlgo.getByName(algorithmName);
        if ("-v".equals(args[1])) {
            if (algo.className == null) {
                logger.error("Algorithm '{}' does not have a visualization.", algorithmName);
                return;
            }
            visualize(algo.className);
        } else {
            run(algo.sortMethod);
        }
    }

    private static void run(final Consumer<int[]> sortMethod) {
        final var elements = random.ints(10, 0, 100).toArray();
        logger.info("Original: {}", elements);
        sortMethod.accept(elements);
        logger.info("Sorted:   {}", elements);
    }

    private static void visualize(final String className) {
        logger.info("Starting visualization");
        final var elements = random.ints(100, 1, 100).toArray();
        final var stringElementStream = Arrays.stream(elements).mapToObj(String::valueOf);
        PApplet.main(
                SortingVisualisation.class,
                Stream.concat(Stream.of(className), stringElementStream).toArray(String[]::new)
        );
    }

}
