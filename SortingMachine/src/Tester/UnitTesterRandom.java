package Tester;

import Sorter.ArraySortingMachine;

import java.util.Random;
import java.util.Scanner;

/**
 * A helper class that lets the user either choose to test an array of size 1000 with randomized elements or
 * let the user choose the size of an array that will be populated with randomized values
 * @author Aaron Heishman
 */
public class UnitTesterRandom {

    /**
     * "Pretty prints" the result of an array with some specific formats.
     * @param array - the array to be printed
     */
    private  void prettyPrint(int[] array) {
        // array length is 1000, so we probably want newLineVal to be something that's easy
        // to divide 1000 by. Something like % 100
        int newLineVal = 20;
        System.out.print("[");
        String f = "%s";
        int oneHundred = 100;
        for (int i = 0; i < array.length; i++) {
            if(i < array.length - 1 && array[i] < oneHundred) {
                System.out.printf("\t%3s, ", array[i]);
            } else if (i < array.length -1) {
                System.out.printf("\t%s, ", array[i]);
            } else {
                System.out.printf("\t%s]\n", array[i]);
            }
            if ((i + 1) % newLineVal == 0) {
                System.out.println();
            }
        }
    }

    /**
     * Prints the results of the array with 1000 elements
     * @param array - the array containing the 1000 randomized elements
     * @param timeInNS - the time the program took to sort the array in nanoseconds
     * @param timeInSeconds - the time the program took to sort the array in seconds
     */
    private  void printOneThousandResults(int[] array, long timeInNS, double timeInSeconds) {
        Scanner s = new Scanner(System.in); // scanner for user input
        System.out.println("Array sorted..printing times...");
        System.out.printf("Total runtime in nanoseconds: %dns\n", timeInNS);
        System.out.printf("Total runtime in seconds: %.2fs\n", timeInSeconds);
        System.out.println("Verifying results are correct...this may take some time..");
        String results = "failed";
        if (checkOrder(array)) {
            results = "passed";
        }
        System.out.printf("Test case of 1000 randomized values...%s!\n", results);
        System.out.print("Enter p to print contents, any other key to exit: ");
        String choice = s.nextLine();
        if (!choice.isBlank() && (choice.charAt(0) == 'p' || choice.charAt(0) == 'P')) {
            prettyPrint(array);
        } else {
            System.out.println("Test done! Goodbye.");
        }
        s.close();
    }

    /**
     * Creates an randomly populated array of size {@code size}
     * @param size - the size of the array
     * @return the ranomly populated array
     */
    private static int[] createRandomizedArray(int size) {
        Random r = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = r.nextInt(size + 1);
        }
        return array;
    }

    /**
     * Checks the order of the array to verify it's in ascending order
     * @param array - the array that's being checked
     * @return true if in order, false otherwise
     */
    private static boolean checkOrder(int[] array) {
        int middle = array.length / 2;
        int first = 0;
        for (int i = 1; i < middle; i++) {
            for (int j = i; j < middle; j++) {
                if (array[first] > array[j]) {
                    return false;
                } else if (array[first + middle] > array[j + middle]) {
                    return false;
                }
            }
            first++;
        }
        return true;
    }

    /**
     * Tests a single randomly populated of size {@code size}
     * @param sortingMachine - the sorting machine used for mergeSort
     * @param size - the size of the array
     * @param testCase - the test case number
     */
    public void testRandom(ArraySortingMachine sortingMachine, int size, int testCase) {
        int[] x = createRandomizedArray(size);
        System.out.printf("Start of Test case #%s\n", testCase);
        System.out.println("\tArray before sort");
        prettyPrint(x);
        sortingMachine.mergeSort(x, 0, x.length - 1);
        System.out.println("\tArray after sort");
        prettyPrint(x);
        if (checkOrder(x)) {
            System.out.printf("Testcase #%s successfully sorted and passed!\n", testCase);
        } else {
            System.out.printf("Test case %s is not sorted! Failed!\n", testCase);
        }
    }


    /**
     * Tests merge sort with 1000 randomized entries while also tracking
     * the execution time
     * @param m - The array sorting machine
     */
    public void testOneThousand(ArraySortingMachine m) {
        long startTime = System.nanoTime();
        double oneMillion = 1000000000.0;
        int n = 1000;
        System.out.println("Starting test with array of size " + n);
        int[] array = createRandomizedArray(n);

        System.out.println("Array created..sorting..");
        m.mergeSort(array, 0, array.length - 1);
        long endTime = System.nanoTime();
        long totalTime = (endTime - startTime);
        double timeInSeconds = totalTime/ oneMillion;
        printOneThousandResults(array, totalTime, timeInSeconds);
    }
}
