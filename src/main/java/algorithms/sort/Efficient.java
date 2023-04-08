package algorithms.sort;

public class Efficient extends Sorting {

    public static <E extends Comparable<E>> void quickSort(E[] arr) {
        quickSort(arr, 1, 0, arr.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] arr, int mode) {
        quickSort(arr, mode, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] arr, int mode, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, mode, left, right);
            quickSort(arr, mode, left, pivotIndex - 1);
            quickSort(arr, mode, pivotIndex + 1, right);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int mode, int left, int right) {
        E pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (compare(arr[j], pivot, mode) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    public static <E extends Comparable<E>> void mergeSort(E[] arr) {
        mergeSort(arr, 1, 0, arr.length - 1);
    }

    public static <E extends Comparable<E>> void mergeSort(E[] arr, int mode) {
        mergeSort(arr, mode, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void mergeSort(E[] arr, int mode, int left, int right) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        mergeSort(arr, mode, left, mid);
        mergeSort(arr, mode, mid + 1, right);
        merge(arr, mode, left, mid, right);
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int mode, int left, int mid, int right) {
        if (!(left <= mid) && (mid <= right))
            return;
        int n, i, j, k;
        n = right - left + 1;
        @SuppressWarnings("unchecked")
        E[] temp = (E[]) new Comparable[n];
        i = left;
        j = mid + 1;
        k = 0;
        while (i <= mid && j <= right) {
            if (compare(arr[i], arr[j], mode) < 0)
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= right)
            temp[k++] = arr[j++];
        k = 0;
        for (i = left; i <= right; i++) {
            arr[i] = temp[k++];
        }
    }

    public static <E extends Comparable<E>> void heapSort(E[] arr) {
        heapSort(arr, 1);
    }

    public static <E extends Comparable<E>> void heapSort(E[] arr, int mode) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heap(arr, mode, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heap(arr, mode, i, 0);
        }
    }

    private static <E extends Comparable<E>> void heap(E[] arr, int mode, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && compare(arr[left], arr[largest], mode) > 0) {
            largest = left;
        }
        if (right < n && compare(arr[right], arr[largest], mode) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heap(arr, mode, n, largest);
        }
    }
}
