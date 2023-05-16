package farbfetzen.algorithms.sorting.sorter;

import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.params.provider.Arguments;

class SortingAlgorithmTestUtil {

    public static final String CLASS_NAME = "farbfetzen.algorithms.sorting.sorter.SortingAlgorithmTestUtil#";
    public static final String PROVIDE_ARRAYS_TO_SORT = CLASS_NAME + "provideArraysToSort";
    public static final String PROVIDE_ARRAYS_TO_NOT_THROW = CLASS_NAME + "provideArraysToNotThrow";

    private static final Random random = new Random();
    @SuppressWarnings("MismatchedReadAndWriteOfArray")
    private static final int[] EMPTY = {};
    private static final int[] SINGLE = {1};
    private static final int[] SMALL = getRandomInts(10, 0, 100);
    private static final int[] MEDIUM = getRandomInts(100, -1000, 1000);
    private static final int[] LARGE = getRandomInts(1000, Integer.MIN_VALUE, Integer.MAX_VALUE);
    private static final int[] SORTED = IntStream.range(0, 100).toArray();
    private static final int[] REVERSED = IntStream.range(0, 100).map(i -> 100 - i - 1).toArray();

    private SortingAlgorithmTestUtil() {
        // Utility class.
    }

    static int[] getRandomInts(final int n, final int min, final int max) {
        final var array = random.ints(n, min, max).toArray();
        while (ArrayUtils.isSorted(array)) {
            ArrayUtils.shuffle(array);
        }
        return array;
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> provideArraysToSort() {
        return Stream.of(
                // @formatter:off
                arguments(named("small array", SMALL.clone())),
                arguments(named("medium array", MEDIUM.clone())),
                arguments(named("large array", LARGE.clone())),
                arguments(named("sorted array", SORTED.clone())),
                arguments(named("reversed array", REVERSED.clone()))
                // @formatter:on
        );
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> provideArraysToNotThrow() {
        return Stream.of(
                // @formatter:off
                arguments(named("single element array", SINGLE.clone())),
                arguments(named("empty array with length zero", EMPTY.clone()))
                // @formatter:on
        );
    }

    static void sortStepwise(final StepWiseSorter sorter) {
        do {
            sorter.step();
        } while (!sorter.isFinished());
    }

}
