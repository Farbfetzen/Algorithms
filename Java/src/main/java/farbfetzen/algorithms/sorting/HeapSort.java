package farbfetzen.algorithms.sorting;

class HeapSort extends StepWiseSorter {

    HeapSort(final int[] array) {
        super(array);
    }

    @Override
    protected void sort() {
        buildHeap();
        for (int i = array.length - 1; i >= 1; i--) {
            steps.add(new SortStepBuilder().swap(array, 0, i).highlight(i).build());
            heapify(0, i);
        }
    }

    private void buildHeap() {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            steps.add(new SortStepBuilder().highlight(i).clearComparisons().build());
            heapify(i, array.length);
        }
    }

    private void heapify(final int i, final int upperBound) {
        var largest = i;
        final var left = i * 2 + 1;
        final var right = i * 2 + 2;
        if (left < upperBound) {
            steps.add(new SortStepBuilder().compare(largest, left).build());
            if (array[largest] < array[left]) {
                largest = left;
            }
        }
        if (right < upperBound) {
            steps.add(new SortStepBuilder().compare(largest, right).build());
            if (array[largest] < array[right]) {
                largest = right;
            }
        }
        if (largest != i) {
            steps.add(new SortStepBuilder().swap(array, largest, i).clearComparisons().build());
            heapify(largest, upperBound);
        }
    }

}
