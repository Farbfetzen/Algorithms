package farbfetzen.algorithms.sorting;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processing.core.PApplet;

import farbfetzen.algorithms.sorting.sorter.HeapSort;
import farbfetzen.algorithms.sorting.sorter.InsertionSort;
import farbfetzen.algorithms.sorting.sorter.QuickSort;
import farbfetzen.algorithms.sorting.sorter.SelectionSort;
import farbfetzen.algorithms.sorting.sorter.StepWiseSorter;

public class SortingAlgorithmRunner {

    @Parameters(commandDescription = "Arguments for sorting algorithms.")
    public static class SortingArguments {

        @Parameter(description = "name", required = true)
        private String name;

        @Parameter(names = "-n", description = "The number of elements to sort.")
        private Integer numberOfElements = 10;

    }

    private static final Logger logger = LoggerFactory.getLogger(SortingAlgorithmRunner.class);
    private static final Map<String, Function<int[], StepWiseSorter>> algorithms = Map.of(
            "heap", HeapSort::new,
            "insertion", InsertionSort::new,
            "quick", QuickSort::new,
            "selection", SelectionSort::new
    );

    public void run(final SortingArguments arguments) {
        final Function<int[], StepWiseSorter> constructor = algorithms.get(arguments.name);
        if (constructor == null) {
            System.err.println("Unknown sorting algorithm '" + arguments.name + "'.");
            return;
        }
        final var elements = IntStream.rangeClosed(1, arguments.numberOfElements).toArray();
        ArrayUtils.shuffle(elements);
        final StepWiseSorter sorter = constructor.apply(elements);

        logger.info("Running {} sort with {} elements.", arguments.name, arguments.numberOfElements);
        logger.info("Starting visualization");
        SortingVisualisation.setAlgorithm(sorter);
        PApplet.main(SortingVisualisation.class);
    }

}
