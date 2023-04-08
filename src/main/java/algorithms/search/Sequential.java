package algorithms.search;

public class Sequential {

    public static <E> int linearSearch(E[] arr, E x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }
}
