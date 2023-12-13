package org.algorithm;

public class QuickSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    static void sort(int[] array, int start, int end) {
        if (start < end) {
            int partitionIndex = partition(array, start, end);
            sort(array, start, partitionIndex - 1);
            sort(array, partitionIndex + 1, end);
        }
    }

    static int partition(int[] array, int start, int end) {
        int pivot = array[end];

        int i = start;
        int j = end;

        while (i < j) {
            if (array[i] < pivot) {
                i++;
                continue;
            }
            while (i < j) {
                if (array[j] >= pivot) {
                    j--;
                    continue;
                }
                swap(array, i, j);
                i++;
                break;
            }
        }
        swap(array, i, end);
        return i;
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
