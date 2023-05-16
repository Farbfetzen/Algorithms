package farbfetzen.algorithms.sorting.sorter;

public class SelectionSort extends StepWiseSorter {

    public SelectionSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        for (int i = array.length - 1; i > 0; i--) {
            steps.add(new SortStepBuilder().highlight(i + 1).clearComparisons().build());
            final var indexOfMaxValue = findIndexOfMaxValue(i);
            steps.add(new SortStepBuilder().swap(array, i, indexOfMaxValue).build());
        }
    }

    private int findIndexOfMaxValue(final int upperBound) {
        var indexOfMaxValue = 0;
        for (int i = 1; i <= upperBound; i++) {
            steps.add(new SortStepBuilder().compare(i, indexOfMaxValue).build());
            if (array[i] > array[indexOfMaxValue]) {
                indexOfMaxValue = i;
            }
        }
        return indexOfMaxValue;

}
    }
