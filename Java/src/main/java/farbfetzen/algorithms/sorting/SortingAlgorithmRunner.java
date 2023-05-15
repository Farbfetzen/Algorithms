package farbfetzen.algorithms.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;

import farbfetzen.algorithms.AlgorithmRunner;

public class SortingAlgorithmRunner implements AlgorithmRunner {

    private static final Logger logger = LoggerFactory.getLogger(SortingAlgorithmRunner.class);

    private static final Random random = new Random();
    private boolean visualize = false;
    private int numberOfElements = 10;
    private String algorithmName;
    private SortAlgo sortAlgo;

    @RequiredArgsConstructor
    enum SortAlgo {
        INSERTION_SORT("insertion sort", InsertionSort::sort, InsertionSort::new),
        SELECTION_SORT("selection sort", SelectionSort::sort, SelectionSort::new),
        HEAP_SORT("heap sort", HeapSort::sort, HeapSort::new),
        QUICK_SORT("quick sort", QuickSort::sort, QuickSort::new);

        final String name;
        final Consumer<int[]> sortMethod;
        final Function<int[], StepWiseSorter> constructor;

        static Optional<SortAlgo> getByName(final String name) {
            return Arrays.stream(values()).filter(v -> v.name.equals(name)).findFirst();
        }
    }

    @Override
    @SuppressWarnings("squid:S127")
    public boolean init(final String algorithmName, final List<String> args) {
        final Optional<SortAlgo> maybeAlgo = SortAlgo.getByName(algorithmName);
        if (maybeAlgo.isEmpty()) {
            return false;
        }
        sortAlgo = maybeAlgo.get();
        this.algorithmName = algorithmName;
        for (int i = 0; i < args.size(); i++) {
            final var arg = args.get(i);
            if ("-v".equals(arg)) {
                visualize = true;
            } else if ("-n".equals(arg)) {
                try {
                    numberOfElements = Integer.parseInt(args.get(++i));
                } catch (final IndexOutOfBoundsException | NumberFormatException e) {
                    throw new IllegalArgumentException("The argument '-n' must be followed by an integer.");
                }
            } else {
                throw new IllegalArgumentException("Unknown argument '" + arg + "'.");
            }
        }
        return true;
    }

    public void run() {
        logger.info("Running {} with {} elements.", algorithmName, numberOfElements);
        if (visualize) {
            visualize(sortAlgo);
        } else {
            run(sortAlgo);
        }
    }

    private void run(final SortAlgo sortAlgo) {
        final var elements = random.ints(numberOfElements, 0, 100).toArray();
        logger.info("Original: {}", elements);
        sortAlgo.sortMethod.accept(elements);
        logger.info("Sorted:   {}", elements);
    }

    private void visualize(final SortAlgo sortAlgo) {
        if (sortAlgo.constructor == null) {
            logger.error("Algorithm '{}' does not have a visualization.", sortAlgo.name);
            return;
        }
        logger.info("Starting visualization");
        final var elements = IntStream.rangeClosed(1, numberOfElements).toArray();
        ArrayUtils.shuffle(elements);
        final var algorithm = sortAlgo.constructor.apply(elements);
        SortingVisualisation.setAlgorithm(algorithm);
        PApplet.main(SortingVisualisation.class);
    }

}
