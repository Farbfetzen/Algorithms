package de.farbfetzen.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static de.farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_NOT_THROW;
import static de.farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_SORT;
import static de.farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_SORT_WITHOUT_LARGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class InsertionSortTest {

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_SORT)
    void shouldSort(final int[] array) {
        InsertionSort.sort(array);
        assertThat(array).isSorted();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_NOT_THROW)
    void shouldNotThrow(final int[] array) {
        assertThatCode(() -> InsertionSort.sort(array)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_SORT_WITHOUT_LARGE)
    void shouldSortStepwise(final int[] array) {
        sortStepwise(array);
        assertThat(array).isSorted();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_NOT_THROW)
    void shouldNotThrowStepwise(final int[] array) {
        assertThatCode(() -> sortStepwise(array)).doesNotThrowAnyException();
    }

    private static void sortStepwise(final int[] array) {
        final StepWiseSorter sorter = new InsertionSort(array);
        do {
            sorter.step();
        } while (!sorter.isFinished());
    }

}
