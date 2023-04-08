package algorithms.search;

public class Interval {

    public static <E extends Comparable<E>> int binarySearch(E[] arr, E x) {
        int sort = checkSort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = sort * arr[mid].compareTo(x);
            if (cmp < 0) {
                left = mid + 1;
            } else if (cmp > 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> int interpolationSearch(E[] arr, E x) {
        int sort = checkSort(arr);
        int left = 0;
        int right = arr.length - 1;
        while (left <= right && sort * x.compareTo(arr[left]) >= 0
                && sort * x.compareTo(arr[right]) <= 0) {
            int pos = left + ((x.compareTo(arr[left]))
                    * (right - left)) / (arr[right].compareTo(arr[left]));
            if (arr[pos].equals(x)) {
                return pos;
            } else if (sort * arr[pos].compareTo(x) < 0) {
                left = pos + 1;
            } else {
                right = pos - 1;
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> int jumpSearch(E[] arr, E x) {
        int sort = checkSort(arr);
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;
        while (sort * arr[Math.min(step, n) - 1].compareTo(x) < 0) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                return -1;
            }
        }
        while (sort * arr[prev].compareTo(x) < 0) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        if (arr[prev].equals(x)) {
            return prev;
        }
        return -1;
    }

    private static <E extends Comparable<E>> int checkSort(E[] arr) {
        int status = sortStatus(arr);
        if (status == 0)
            throw new IllegalArgumentException("Array is not sorted");
        return status;
    }

    private static <E extends Comparable<E>> int sortStatus(E[] arr) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0)
                isIncreasing = false;
            if (arr[i].compareTo(arr[i + 1]) < 0)
                isDecreasing = false;
            if (!isIncreasing && !isDecreasing)
                return 0;
        }
        return isIncreasing ? 1 : -1;
    }
}
