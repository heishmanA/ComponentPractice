package Tester;

import Sorter.ArraySortingMachine;

import java.util.Arrays;

/**
 * A helper class for static tests where the expected cases and test cases
 * are hard coded into the class and not randomized.
 * @author Aaron Heishman
 */
public class UnitTesterStatic {
    ArraySortingMachine sortingMachine;
    /**
     * Construct a new instance of the static unit tester
     */
    public UnitTesterStatic() {
        sortingMachine = new ArraySortingMachine();
    }

    /**
     * Check if two arrays are equal
     * @param expected - the expected array
     * @param test - the test array
     * @return true if for all values [i] in expected and test, expected[i] == test[i], false otherwise
     */
    private boolean isEqual(int[] expected, int[] test) {
        /* Arrays should match lengths, but a check regardless*/
        if (expected.length != test.length) {
            return false;
        }
        /* just iterating through both loops */
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != test[i]) {
                return false;
            }
        }
        /* if the method makes it here, both match */
        return true;
    }

    /**
     * Helper method for just printing values to save time
     * @param expected - the expected array
     * @param test - the test array
     */
    private void printTestCase(int[] expected, int[] test){
        System.out.printf("Expected : %s | Test : %s\n", Arrays.toString(expected), Arrays.toString(test));
    }

    /**
     * Tests sorting the given array in {@code test} and then comparing it to {@code expected}
     * @param testCase - the test case number
     * @param expected - the expected sorted array
     * @param test - the test array to be sorted
     */
    private void test(int testCase, int[] expected, int[] test) {
        System.out.println("-----------------------------");
        System.out.printf("Running Test Case #%d with size %d\n", testCase, expected.length);
        printTestCase(expected, test);
        int first = 0;
        sortingMachine.mergeSort(test, first, test.length - 1);
        System.out.printf("Printing results of Test Case #%d\n", testCase);
        String f = "Failed";
        if (isEqual(expected, test)) {
            f = "Passed";
        }
        System.out.printf("Test Case #%d has %s! Printing arrays..\n", testCase, f);
        printTestCase(expected, test);
    }

    /**
     * Starts the test case with 10 non-randomized arrays
     */
    public void startTests() {
        int [][] expected = {
                { 2, 5, 10, 11 },
                { 1, 3, 6, 7, 9 },
                { 2, 4, 8 },
                { 3, 6, 9, 12, 15, 18 },
                { 2, 5, 8, 11, 14, 17, 20 },
                { 1, 1, 2, 3, 5, 8, 13 },
                { 1, 1, 2, 3, 5, 8, 13, 21, 34 },
                { 5, 10, 15, 20, 25, 30 },
                { 5, 10, 15, 20, 25, 30 },
                { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29}
        };

        int[][] testCases = {
                { 10, 2, 5, 11 },
                { 7, 3, 9, 1, 6 },
                { 8, 4, 2 },
                { 12, 15, 9, 3, 6, 18 },
                { 20, 17, 14, 11, 8, 5, 2 },
                { 1, 1, 2, 3, 5, 8, 13 },
                { 21, 34, 13, 8, 5, 3, 2, 1, 1 },
                { 5, 10, 15, 20, 25, 30 },
                { 30, 25, 20, 15, 10, 5 },
                { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29 }
        };

        int size = expected.length;
        for (int i = 0; i < size; i++) {
            test(i+1, expected[i], testCases[i]);
        }
    }
}
