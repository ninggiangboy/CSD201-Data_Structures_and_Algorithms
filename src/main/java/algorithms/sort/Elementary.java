package algorithms.sort;

public class Elementary extends Sorting {

    public static <E extends Comparable<E>> void bubbleSort(E[] arr) {
        bubbleSort(arr, 1);
    }

    public static <E extends Comparable<E>> void bubbleSort(E[] arr, int mode) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (compare(arr[i], arr[i + 1], mode) > 0) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static <E extends Comparable<E>> void selectionSort(E[] arr) {
        selectionSort(arr, 1);
    }

    public static <E extends Comparable<E>> void selectionSort(E[] arr, int mode) {
        int n = arr.length;
        int i, j, k;
        E min;
        for (i = 0; i < n - 1; i++) {
            min = arr[i];
            k = i;
            for (j = i + 1; j < n; j++) {
                if (compare(arr[j], min, mode) < 0) {
                    k = j;
                    min = arr[j];
                }
            }
            if (k != i)
                swap(arr, i, k);
        }
    }

    public static <E extends Comparable<E>> void insertionSort(E[] arr) {
        insertionSort(arr, 1);
    }

    public static <E extends Comparable<E>> void insertionSort(E[] arr, int mode) {
        int n = arr.length;
        int i, j;
        E x;
        for (i = 1; i < n; i++) {
            x = arr[i];
            j = i;
            while (j > 0 && compare(x, arr[j - 1], mode) < 0) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = x;
        }
    }
}
