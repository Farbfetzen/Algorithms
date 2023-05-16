package farbfetzen.algorithms.sorting;

import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_NOT_THROW;
import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_SORT;
import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.sortStepwise;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class QuickSortTest {

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_SORT)
    void shouldSort(final int[] array) {
        sortStepwise(new QuickSort(array));
        assertThat(array).isSorted();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_NOT_THROW)
    void shouldNotThrow(final int[] array) {
        assertThatCode(() -> sortStepwise(new QuickSort(array))).doesNotThrowAnyException();
    }

}
