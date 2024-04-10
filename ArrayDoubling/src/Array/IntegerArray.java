package Array;

import java.util.Arrays;

/**
 * Array.Array class that uses table doubling
 */
public class IntegerArray {
    private int[] arrayRep;
    private int next;
    private int filledSlots;
    private final int initialSize;
    public IntegerArray(int initialSize) {
        this.next = 0;
        this.filledSlots = 0;
        if (initialSize == 0) {
            this.initialSize = 1; // can't double an empty array so just make the starting size = 1
        } else {
            this.initialSize = initialSize;
        }
        this.arrayRep = new int[this.initialSize];
    }

    /**
     * Updates this.arrayRep based on the new size of the array. Places all values back in order starting from the front
     * until size
     * @param newSize the new size of the array
     */
    private void updateArraySize(int newSize) {
        int[] newArray = new int[newSize];
        filledSlots = 0;
        for (int i = 0; i < next; i++) {
            newArray[i] = this.arrayRep[i];
            filledSlots++;
        }
        this.arrayRep = newArray;
    }

    /**
     * Double the size of the array
     */
    private void doubleSize() {
        int newSize = filledSlots * 2;
        updateArraySize(newSize);
    }

    /**
     * Halves the size of the array
     */
    private void halveSize() {
        int newSize = arrayRep.length / 2;
        if (newSize < initialSize) {
            newSize = initialSize;
        }
        updateArraySize(newSize);
    }

    /**
     * Append a value to the end of the array
     * @param x - the value to be appended
     */
    public void append(int x) {
        this.arrayRep[next] = x;
        this.filledSlots++;
        this.next++;
        if (filledSlots == arrayRep.length) {
            doubleSize();
        }

    }

    /**
     * Removes and returns the value located at the end of this
     * @return the value at the end of this
     */
    public int remove() {
        assert filledSlots > 0 : "Assertion Error: Remove called on empty array";
        int x = arrayRep[next - 1];
        this.filledSlots--;
        if (arrayRep.length > initialSize && filledSlots <= (arrayRep.length)/4) {
            halveSize();
        }
        next--;
        return x;
    }

    /**
     * Returns the index of the given value
     * @param x the value to be found
     * @return the index if x is in the array, -1 otherwise
     */
    public int indexOf(int x) {
        int temp_size = filledSlots;
        for (int i = 0; i < temp_size; i++) {
            if (arrayRep[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets a value at location i if and only if 0 <= i < this.size
     * @param i the index of the value to be obtained
     * @return the value at the given index
     */
    public int get(int i) {
        assert i >= 0 && i < this.filledSlots : "Assertion Error on Get: Index out of bounds error";
        return arrayRep[i];
    }

    public int length() {
        return this.arrayRep.length;
    }

    /**
     * Gets the amount of filled slots in the array and returns that
     * @return the total amount of elements in the array
     */
    public int filledSlots() {
        return this.filledSlots;
    }

    /**
     * Pretty prints the array
     */
    public void printArray() {
        System.out.print("[");
        for (int i = 0; i < this.next; i++) {
            System.out.print(this.arrayRep[i]);
            if (i < this.next - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }


}
