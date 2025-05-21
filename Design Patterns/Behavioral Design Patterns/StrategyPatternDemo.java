import java.util.Arrays;

public class StrategyPatternDemo {

    public static void main(String[] args) {
        SortingContext sortingContext = new SortingContext(new BubbleSortStrategy());

        int[] array1 = {5, 2, 9, 1, 5};
        sortingContext.performSort(array1);

        sortingContext.setSortingStrategy(new MergeSortStrategy());
        int[] array2 = {8, 3, 7, 4, 2};
        sortingContext.performSort(array2);

        sortingContext.setSortingStrategy(new QuickSortStrategy());
        int[] array3 = {6, 1, 3, 9, 5};
        sortingContext.performSort(array3);
    }

    // Strategy Interface
    interface SortingStrategy {
        void sort(int[] array);
    }

    // Concrete Strategy - Bubble Sort
    static class BubbleSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
            System.out.println("Sorted using Bubble Sort: " + Arrays.toString(array));
        }
    }

    // Concrete Strategy - Merge Sort
    static class MergeSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            mergeSort(array, 0, array.length - 1);
            System.out.println("Sorted using Merge Sort: " + Arrays.toString(array));
        }

        private void mergeSort(int[] array, int left, int right) {
            if (left < right) {
                int mid = left + (right - left) / 2;
                mergeSort(array, left, mid);
                mergeSort(array, mid + 1, right);
                merge(array, left, mid, right);
            }
        }

        private void merge(int[] array, int left, int mid, int right) {
            int leftSize = mid - left + 1;
            int rightSize = right - mid;

            int[] leftArray = new int[leftSize];
            int[] rightArray = new int[rightSize];

            for (int i = 0; i < leftSize; i++)
                leftArray[i] = array[left + i];
            for (int j = 0; j < rightSize; j++)
                rightArray[j] = array[mid + 1 + j];

            int i = 0, j = 0, k = left;
            while (i < leftSize && j < rightSize) {
                if (leftArray[i] <= rightArray[j]) {
                    array[k++] = leftArray[i++];
                } else {
                    array[k++] = rightArray[j++];
                }
            }
            while (i < leftSize)
                array[k++] = leftArray[i++];
            while (j < rightSize)
                array[k++] = rightArray[j++];
        }
    }

    // Concrete Strategy - Quick Sort
    static class QuickSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            quickSort(array, 0, array.length - 1);
            System.out.println("Sorted using Quick Sort: " + Arrays.toString(array));
        }

        private void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pivotIndex = partition(array, low, high);
                quickSort(array, low, pivotIndex - 1);
                quickSort(array, pivotIndex + 1, high);
            }
        }

        private int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            return i + 1;
        }
    }

    // Context Class
    static class SortingContext {
        private SortingStrategy sortingStrategy;

        public SortingContext(SortingStrategy sortingStrategy) {
            this.sortingStrategy = sortingStrategy;
        }

        public void setSortingStrategy(SortingStrategy sortingStrategy) {
            this.sortingStrategy = sortingStrategy;
        }

        public void performSort(int[] array) {
            sortingStrategy.sort(array);
        }
    }
}
