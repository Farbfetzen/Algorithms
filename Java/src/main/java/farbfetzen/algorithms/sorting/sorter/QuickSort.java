package farbfetzen.algorithms.sorting.sorter;

public class QuickSort extends StepWiseSorter {

    public QuickSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        quickSort(0, array.length - 1);
    }

    private void quickSort(final int leftIndex, final int rightIndex) {
        if (leftIndex < rightIndex) {
            final var pivotIndex = partition(leftIndex, rightIndex);
            quickSort(leftIndex, pivotIndex - 1);
            quickSort(pivotIndex + 1, rightIndex);
        }
    }

    private int partition(final int leftIndex, final int rightIndex) {
        // If there are only two values then sort them immediately.
        if (rightIndex - leftIndex < 2) {
            final var leftValue = array[leftIndex];
            final var rightValue = array[rightIndex];
            steps.add(new SortStepBuilder().compare(leftIndex, rightIndex).build());
            if (leftValue > rightValue) {
                steps.add(new SortStepBuilder().swap(array, leftIndex, rightIndex).build());
            }
            return leftIndex;
        }
        final var pivotIndex = (leftIndex + rightIndex) / 2;
        steps.add(new SortStepBuilder().highlight(pivotIndex).clearComparisons().build());
        final var pivotValue = array[pivotIndex];
        steps.add(new SortStepBuilder().swap(array, pivotIndex, rightIndex).highlight(rightIndex).build());
        var storeIndex = leftIndex;
        for (int i = leftIndex; i < rightIndex; i++) {
            steps.add(new SortStepBuilder().compare(i, rightIndex).build());
            if (array[i] <= pivotValue) {
                steps.add(new SortStepBuilder().swap(array, i, storeIndex).highlight(storeIndex + 1).build());
                storeIndex++;
            }
        }
        steps.add(new SortStepBuilder().swap(array, rightIndex, storeIndex).build());
        return storeIndex;
    }

}
