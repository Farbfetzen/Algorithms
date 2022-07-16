package de.farbfetzen.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static de.farbfetzen.algorithms.sorting.SortingAlgorithmTestConstants.PROVIDE_ARRAYS_TO_NOT_THROW;
import static de.farbfetzen.algorithms.sorting.SortingAlgorithmTestConstants.PROVIDE_ARRAYS_TO_SORT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class SelectionSortTest {

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_SORT)
    void shouldSort(final int[] array) {
        SelectionSort.sort(array);
        assertThat(array).isSorted();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_NOT_THROW)
    void shouldNotThrow(final int[] array) {
        assertThatCode(() -> SelectionSort.sort(array)).doesNotThrowAnyException();
    }

}
