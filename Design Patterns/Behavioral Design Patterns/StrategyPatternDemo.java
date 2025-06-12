/*
 * Strategy Design Pattern
 *
 * Purpose:
 * - Define a family of algorithms, encapsulate each one, and make them interchangeable.
 * - Lets the algorithm vary independently from clients that use it.
 *
 * Real-World Analogy:
 * - Imagine a navigation app (like Google Maps). You can choose a strategy like "fastest route", "shortest distance",
 *   or "avoid tolls". The app delegates the route calculation to the chosen strategy.
 *
 * Workflow Summary:
 * StrategyPatternDemo (main)
 *   ├── Creates SortingContext with initial strategy (e.g., BubbleSortStrategy)
 *   ├── SortingContext delegates sorting task to current strategy via `performSort()`
 *   │     ├── BubbleSortStrategy handles sorting and prints output
 *   ├── Strategy switched to MergeSortStrategy via `setSortingStrategy()`
 *   │     ├── MergeSortStrategy handles sorting
 *   ├── Strategy switched to QuickSortStrategy
 *   │     ├── QuickSortStrategy handles sorting
 *
 * Key Components:
 * 1. Strategy Interface (SortingStrategy): Declares the sorting behavior
 * 2. Concrete Strategies (BubbleSortStrategy, MergeSortStrategy, QuickSortStrategy): Implement the behavior
 * 3. Context (SortingContext): Maintains a reference to a Strategy object and delegates sorting to it
 *
 * When to Use:
 * - You need multiple variants of an algorithm (e.g., different sorting methods)
 * - You want to isolate algorithm implementation from the client
 * - You want to replace conditional logic (like switch or if-else) with polymorphism
 *
 * Pros:
 * - Eliminates complex conditional statements
 * - Supports open/closed principle (add new strategies without changing existing code)
 * - Improves code flexibility and testability
 *
 * Cons:
 * - Client must be aware of the different strategies
 * - Increases number of classes (one per strategy)
 */


import java.util.Arrays;

public class StrategyPatternDemo {

    // Client: Main method selects and switches sorting algorithms at runtime
    public static void main(String[] args) {
        // Initial strategy: Bubble Sort
        SortingContext sortingContext = new SortingContext(new BubbleSortStrategy());
        int[] array1 = {5, 2, 9, 1, 5};
        sortingContext.performSort(array1);  // Output: Sorted using Bubble Sort

        // Switch to Merge Sort
        sortingContext.setSortingStrategy(new MergeSortStrategy());
        int[] array2 = {8, 3, 7, 4, 2};
        sortingContext.performSort(array2);  // Output: Sorted using Merge Sort

        // Switch to Quick Sort
        sortingContext.setSortingStrategy(new QuickSortStrategy());
        int[] array3 = {6, 1, 3, 9, 5};
        sortingContext.performSort(array3);  // Output: Sorted using Quick Sort
    }

    // 1. Strategy Interface
    interface SortingStrategy {
        void sort(int[] array);  // Defines sorting behavior
    }

    // 2. Concrete Strategy - Bubble Sort
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

    // 2. Concrete Strategy - Merge Sort
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

    // 2. Concrete Strategy - Quick Sort
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

    // 3. Context Class
    static class SortingContext {
        private SortingStrategy sortingStrategy; // Holds current strategy

        public SortingContext(SortingStrategy sortingStrategy) {
            this.sortingStrategy = sortingStrategy;
        }

        public void setSortingStrategy(SortingStrategy sortingStrategy) {
            this.sortingStrategy = sortingStrategy; // Dynamically change strategy
        }

        public void performSort(int[] array) {
            sortingStrategy.sort(array); // Delegate sorting to strategy
        }
    }
}

