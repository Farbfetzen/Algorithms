package farbfetzen.algorithms.sorting.sorter;

public class InsertionSort extends StepWiseSorter {

    public InsertionSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        for (int i = 0; i < array.length; i++) {
            var j = i - 1;
            final var currentValue = array[i];
            steps.add(new SortStepBuilder().highlight(i).clearComparisons().build());
            for (; j >= 0; j--) {
                steps.add(new SortStepBuilder().compare(j, j + 1).highlight(i).build());
                if (array[j] > currentValue) {
                    steps.add(new SortStepBuilder().swap(array, j, j + 1).build());
                } else {
                    break;
                }
            }
        }
    }

}
