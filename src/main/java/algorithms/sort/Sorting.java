package algorithms.sort;

public class Sorting {

    protected static <E extends Comparable<E>> int compare(E e1, E e2, int mode) {
        if (mode == 1) {
            return e1.compareTo(e2);
        } else if (mode == -1) {
            return e2.compareTo(e1);
        }
        return 0;
    }

    protected static <E extends Comparable<E>> void swap(E[] a, int i, int j) {
        E temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
