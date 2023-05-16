package farbfetzen.algorithms.sorting;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;

import farbfetzen.algorithms.AlgorithmRunner;

public class SortingAlgorithmRunner implements AlgorithmRunner {

    private static final Logger logger = LoggerFactory.getLogger(SortingAlgorithmRunner.class);
    private static final Map<String, Function<int[], StepWiseSorter>> algorithms = Map.of(
            "insertion sort", InsertionSort::new,
            "selection sort", SelectionSort::new,
            "heap sort", HeapSort::new,
            "quick sort", QuickSort::new
    );

    private int numberOfElements = 10;

    @Override
    public boolean hasAlgorithm(final String algorithmName) {
        return algorithms.containsKey(algorithmName);
    }

    @Override
    public void run(final String algorithmName, final List<String> args) {
        final Function<int[], StepWiseSorter> constructor = algorithms.get(algorithmName);
        if (constructor == null) {
            return;
        }
        parseArguments(args);
        final var elements = IntStream.rangeClosed(1, numberOfElements).toArray();
        ArrayUtils.shuffle(elements);
        final StepWiseSorter sorter = constructor.apply(elements);

        logger.info("Running {} with {} elements.", algorithmName, numberOfElements);
        logger.info("Starting visualization");
        SortingVisualisation.setAlgorithm(sorter);
        PApplet.main(SortingVisualisation.class);
    }

    private void parseArguments(final List<String> args) {
        for (int i = 0; i < args.size(); i++) {
            final var arg = args.get(i);
            if ("-n".equals(arg)) {
                try {
                    numberOfElements = Integer.parseInt(args.get(++i));
                } catch (final IndexOutOfBoundsException | NumberFormatException e) {
                    throw new IllegalArgumentException("The argument '-n' must be followed by an integer.");
                }
            } else {
                throw new IllegalArgumentException("Unknown argument '" + arg + "'.");
            }
        }
    }

}
