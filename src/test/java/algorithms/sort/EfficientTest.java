package algorithms.sort;

import utils.ArrayUtils;
import utils.Inputted;
import utils.Menu;

import java.util.Arrays;

import static algorithms.sort.Efficient.*;

class EfficientTest {

    private static final String[] menu = {
            "Quick Sort",
            "Merge Sort",
            "Heap Sort"
    };

    public static void main(String[] args) {
        int[] arr = new int[20];
        ArrayUtils.generateRandomElement(arr, -10, 10);
        Integer[] integerArr = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        System.out.println("Unsorted: " + Arrays.toString(integerArr));

        int mode = Inputted.inputIntNumber("Input mode: ", "Invalid", "Out of range", -1, 1);
        switch (Menu.getChoice(menu)) {
            case 1:
                quickSort(integerArr, mode);
                break;
            case 2:
                mergeSort(integerArr, mode);
                break;
            case 3:
                heapSort(integerArr, mode);
                break;
        }
        System.out.println("Sorted: " + Arrays.toString(integerArr));
    }
}