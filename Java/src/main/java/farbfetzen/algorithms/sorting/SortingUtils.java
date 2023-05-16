package farbfetzen.algorithms.sorting;

public class SortingUtils {

    private SortingUtils() {
        // Utility class.
    }

    public static void swap(final int[] array, final int i, final int j) {
        if (i != j) {
            final var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
