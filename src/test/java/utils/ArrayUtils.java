package utils;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {
    /**
     * Generates random integers between the specified minimum and maximum values
     * and stores them in the given array.
     *
     * @param arr the array to store the generated random integers in.
     * @param min the minimum value of the range of possible random integers
     *            (inclusive).
     * @param max the maximum value of the range of possible random integers
     *            (inclusive).
     *
     * @throws IllegalArgumentException if the given array is null or has a length
     *                                  of 0, or if the minimum value is greater
     *                                  than the maximum value.
     */
    public static void generateRandomElement(int[] arr, int min, int max) {
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt((max - min) + 1) + min;
        }
    }

    /**
     * Prints the contents of the given array to the console in a formatted string.
     *
     * @param nameArray the name of the array being printed.
     * @param arr       the array to be printed.
     *
     * @throws IllegalArgumentException if the given array is null.
     */
    public static void printArray(String nameArray, int[] arr) {
        System.out.println(nameArray + ": " + Arrays.toString(arr));
    }

    /**
     * 
     * This method checks if the given integer array is sorted in increasing or
     * decreasing order, or not sorted at all.
     * 
     * @param arr the integer array to be checked
     * @return 0 if the array is not sorted, 1 if the array is sorted in increasing
     *         order, and 2 if the array is sorted in decreasing order.
     */
    public static int checkSort(int[] arr) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] >= arr[i + 1])
                isIncreasing = false;
            else if (arr[i] <= arr[i + 1])
                isDecreasing = false;
            if (!isIncreasing && !isDecreasing)
                return 0;
        }
        return isIncreasing ? 1 : 2;
    }

    /**
     * 
     * This method prints the search result to the console.
     * 
     * @param indexSearch  The index of the searched number in the array. If the
     *                     number is not found, this value will be -1.
     * @param numberSearch The number that is being searched for in the array.
     *                     The method checks if the index of the searched number is
     *                     equal to -1. If it is, then the method prints "Not found"
     *                     along with the number that was searched. If the index is
     *                     not equal to -1, then the method prints "Found" along
     *                     with the number that was searched, and the index at which
     *                     the number was found.
     */
    public static void printResult(int indexSearch, int numberSearch) {
        System.out.println(indexSearch == -1 ? "Not found " + numberSearch
                : "Found " + numberSearch + " at index: " + indexSearch);
    }
}
