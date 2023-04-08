package algorithms.search;

import algorithms.sort.Efficient;
import utils.ArrayUtils;
import utils.Inputted;

import java.util.Arrays;

import static algorithms.search.Sequential.*;

class SequentialTest {

    public static void main(String[] args) {
        int[] arr = new int[20];
        ArrayUtils.generateRandomElement(arr, -20, 20);
        Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Efficient.quickSort(integerArr);
        System.out.println("Array: " + Arrays.toString(integerArr));

        int x = Inputted.inputIntNumber("Enter search number: ", "Invalid", "Out of range", -20, 20);
        int index = linearSearch(integerArr, x);
        System.out.println("Index: " + index);
    }
}