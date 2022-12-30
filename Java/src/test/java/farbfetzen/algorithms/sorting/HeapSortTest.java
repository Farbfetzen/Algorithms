package farbfetzen.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_NOT_THROW;
import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_SORT;
import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.PROVIDE_ARRAYS_TO_SORT_WITHOUT_LARGE;
import static farbfetzen.algorithms.sorting.SortingAlgorithmTestUtil.sortStepwise;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class HeapSortTest {

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_SORT)
    void shouldSort(final int[] array) {
        HeapSort.sort(array);
        assertThat(array).isSorted();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_NOT_THROW)
    void shouldNotThrow(final int[] array) {
        assertThatCode(() -> HeapSort.sort(array)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_SORT_WITHOUT_LARGE)
    void shouldSortStepwise(final int[] array) {
        sortStepwise(new HeapSort(array));
        assertThat(array).isSorted();
    }

    @ParameterizedTest
    @MethodSource(PROVIDE_ARRAYS_TO_NOT_THROW)
    void shouldNotThrowStepwise(final int[] array) {
        assertThatCode(() -> sortStepwise(new HeapSort(array))).doesNotThrowAnyException();
    }

}
