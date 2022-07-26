package de.farbfetzen.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import processing.core.PApplet;

import de.farbfetzen.algorithms.AlgorithmRunner;

@Slf4j
public class SortingAlgorithmRunner implements AlgorithmRunner {

    private static final Random random = new Random();

    @RequiredArgsConstructor
    enum SortAlgo {
        INSERTION_SORT("insertion sort", InsertionSort::sort, InsertionSort::new),
        SELECTION_SORT("selection sort", SelectionSort::sort, SelectionSort::new),
        HEAP_SORT("heap sort", HeapSort::sort, null);

        final String name;
        final Consumer<int[]> sortMethod;
        final StepWiseSorter.Constructor constructor;

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
        final var sortAlgo = SortAlgo.getByName(algorithmName);
        if (args.length > 1 && "-v".equals(args[1])) {
            visualize(sortAlgo);
        } else {
            run(sortAlgo);
        }
    }

    private static void run(final SortAlgo sortAlgo) {
        final var elements = random.ints(10, 0, 100).toArray();
        logger.info("Original: {}", elements);
        sortAlgo.sortMethod.accept(elements);
        logger.info("Sorted:   {}", elements);
    }

    private static void visualize(final SortAlgo sortAlgo) {
        if (sortAlgo.constructor == null) {
            logger.error("Algorithm '{}' does not have a visualization.", sortAlgo.name);
            return;
        }
        logger.info("Starting visualization");
        final var elements = IntStream.rangeClosed(1, 100).toArray();
        ArrayUtils.shuffle(elements);
        final var algorithm = sortAlgo.constructor.construct(elements);
        SortingVisualisation.setAlgorithm(algorithm);
        PApplet.main(SortingVisualisation.class);
    }

}
