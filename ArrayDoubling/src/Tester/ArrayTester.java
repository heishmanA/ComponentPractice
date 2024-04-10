package Tester;

import Array.IntegerArray;

import java.util.Arrays;

/**
 * Test cases for testing an array that takes integers and utilizes table doubling
 */
public class ArrayTester {

    /**
     * Creates and populates a new IntegerArray
     * @param initialSize the starting size of the array
     * @param args the elements to add to the array
     * @return a new integer array of size initialSize populated with the elements of args
     */
    private IntegerArray createTestArray(int initialSize, int... args) {
        IntegerArray arr = new IntegerArray(initialSize);
        for (int arg : args) {
            arr.append(arg);
        }
        return arr;
    }

    /**
     * Creates and populates an array with the given arguments
     * @param args the elements to be in the array
     * @return an integer array populated with the elements of args
     */
    private int[] createExpectedArray(int... args) {
        int[] x = new int[args.length];
        System.arraycopy(args, 0, x, 0, args.length);
        return x;
    }

    /**
     * Compares testArray to expectedArray
     * @param testArray reference to the IntegerArray being tested
     * @param expected reference to the expected Array to compare to
     * @return true if testArray matches expectedArray, false otherwise
     */
    private boolean compare(IntegerArray testArray, int[] expected) {
        // if the amount of filled slots of the integer array does not match the length of
        // expected then return false
        if (testArray.filledSlots() != expected.length) {
            return false;
        }
        // check if all the values match the given array
        for (int i = 0; i < testArray.filledSlots(); i++) {
            if (testArray.get(i) != expected[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Simple printing method
     * @param passed string representing pass/fail
     * @param testName string representing the name of the function
     */
    private void printResults(String passed, String testName) {
        System.out.printf("%s has %s!\n", testName, passed);
    }

    /**
     * Lazy way to start comparison rather than typing it constantly
     * @param testArray reference to the array being tested
     * @param expectedArray reference to the expected array
     * @param testName the name of the function being tested
     */
    private void doComparison(IntegerArray testArray, int[] expectedArray, String testName) {
        String s = "";
        if (compare(testArray, expectedArray)) {
            s = "passed";
        } else {
            s = "failed";
        }
        printResults(s, testName);
    }

    // ----------------------------------------- Tests --------------------------

    /**
     * Run every test in the tester
     */
    public void testAll() {
        this.testEmpty();
        this.testSizeEmpty();
        this.testAppendEmpty();
        this.testAppendFilledNoDoubling();
        this.testSizeAppendEmpty();
        this.testDoubling1();
        this.testDoubling2();
        this.testRemoveMakeEmpty();
        this.testRemoveNonEmpty();
        this.testTableHalving();
        this.testIndexOfFound();
        this.testIndexOfNotFound();
    }

    public void testEmpty() {
        int[] expected = new int[0];
        IntegerArray testArray = createTestArray(0);
        doComparison(testArray, expected, "testEmpty");
    }

    public void testAppendEmpty() {
        int[] expected = createExpectedArray(10);
        IntegerArray test = new IntegerArray(0);
        test.append(10);
        doComparison(test, expected, "testAppendEmpty");
    }

    public void testAppendFilledNoDoubling() {
        int[] expected = createExpectedArray(5, 10, 2, 3, 8);
        IntegerArray test = createTestArray(5, 5, 10, 2, 3);
        test.append(8);
        doComparison(test, expected, "testAppendFilledNoDoubling");
    }

    public void testDoubling1() {
        int initialSize = 4;
        int expectedSize = 8;
        IntegerArray test = createTestArray(initialSize, 0, 1, 2, 3);
        String result = "failed";
        if (test.length() == expectedSize) {
            result = "passed";
        }
        printResults(result, "testDoubling1()");
    }

    public void testDoubling2() {
        int initialSize = 4;
        int[] expected = createExpectedArray(0, 1, 2, 3, 4);
        IntegerArray test = createTestArray(initialSize, 0, 1, 2, 3, 4);
        doComparison(test, expected, "testDoubling2");
    }

    public void testRemoveMakeEmpty() {
        IntegerArray test = createTestArray(4, 25);
        int x = test.remove();
        String result = "failed";
        if (x == 25) {
            result = "passed";
        }
        printResults(result, "testRemoveMakeEmpty()");
    }

    public void testRemoveNonEmpty() {
        IntegerArray test = createTestArray(4, 16, 81, 32, 45, 81, 22);
        int x = test.remove();
        String result = "failed";
        if (x == 22) {
            result = "passed";
        }
        printResults(result, "testRemoveNonEmpty()");
    }

    public void testSizeEmpty() {
        int[] expected = new int[0];
        IntegerArray testArray = createTestArray(0);
        String passed;
        if (testArray.filledSlots() == expected.length) {
            passed = "passed";
        } else {
            passed = "failed";
        }
        printResults(passed, "testSizeEmpty");
    }

    public void testSizeAppendEmpty() {
        int[] expected = createExpectedArray(10);
        IntegerArray testArray = new IntegerArray(0);
        testArray.append(10);
        String passed;
        if (testArray.filledSlots() == expected.length) {
            passed = "passed";
        } else {
            passed = "failed";
        }
        printResults(passed, "testSizeEmpty");
    }

    public void testTableHalving() {
        int initialSize = 4; // size before doubling
        int expectedSize = 8; // after halving
        IntegerArray test = createTestArray(initialSize, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        // now to remove until we have 4 filled slots
        while (test.filledSlots() > initialSize) {
            test.remove();
        }
        String result = "failed";
        if (test.length() == expectedSize) {
            result = "passed";
        }
        printResults(result, "testTableHalving");
    }

    public void testIndexOfFound() {
        int initialSize = 8;
        IntegerArray test = createTestArray(initialSize, 1, 2, 3, 4);
        String result = "failed";
        int expectedIndex = 2;
        int val = 3;
        if (test.indexOf(val) == expectedIndex) {
            result = "passed";
        }
        printResults(result, "testIndexOfFound");
    }

    public void testIndexOfNotFound() {
        int initialSize = 8;
        IntegerArray test = createTestArray(initialSize, 1, 2, 3, 4);
        String result = "failed";
        int expectedIndex = -1;
        int val = 99;
        if (test.indexOf(val) == expectedIndex) {
            result = "passed";
        }
        printResults(result, "testIndexOfNotFound");
    }

}
