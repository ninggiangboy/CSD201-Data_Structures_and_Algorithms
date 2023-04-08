package algorithms.search;

import algorithms.sort.Efficient;
import utils.ArrayUtils;
import utils.Inputted;
import utils.Menu;

import java.util.Arrays;

import static algorithms.search.Interval.*;

class IntervalTest {

    private static final String[] menu = {
            "Binary Search",
            "Interpolation Search",
            "Jump search"
    };

    public static void main(String[] args) {
        int[] arr = new int[20];
        ArrayUtils.generateRandomElement(arr, -20, 20);
        Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Efficient.quickSort(integerArr);
        System.out.println("Array: " + Arrays.toString(integerArr));

        int x = Inputted.inputIntNumber("Enter search number: ", "Invalid", "Out of range", -20, 20);
        int index = 0;
        switch (Menu.getChoice(menu)) {
            case 1:
                index = binarySearch(integerArr, x);
                break;
            case 2:
                index = interpolationSearch(integerArr, x);
                break;
            case 3:
                index = jumpSearch(integerArr, x);
                break;
        }
        System.out.println("Index: " + index);
    }
}