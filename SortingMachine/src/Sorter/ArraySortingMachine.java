package Sorter;

public class ArraySortingMachine {

    /**
     * Splits, sorts and merges the main array
     * @param array - the main array being sorted
     * @param first - the index of the first element to be sorted
     * @param middle - the index of the middle of the array
     * @param last - the index of the last value in the array
     */
    private void merge(int[] array, int first, int middle, int last) {
        int leftSize = middle - first + 1;
        int rightSize = last - middle;
        int[] left = new int[leftSize];
        int[] right = new int[rightSize];
        split(array, left, right, first, middle);
        int i = 0, j = 0, k = first;
        /*
         * Sort until either the left or right side has been completed
         */
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }
        /*
         * Copy Any values left over
         */
        k = copyTheRest(left, array, i, k);
        copyTheRest(right, array, j, k);
    }
    private void split(int[] array, int[] left, int[] right, int first, int middle) {
        /*
         * Copy elements from array[first, middle] to the left array
         * then copy element from array[middle+1, last] to the right array
         */
        System.arraycopy(array, first, left, 0, left.length);
        System.arraycopy(array, middle+1, right, 0, right.length);
    }
    /**
     * Copies the rest of the values in either the left or right side to the main array
     * @param leftOrRightArray - the left or right sub-array
     * @param mainArray - the main array that is being sorted
     * @param leftOrRightPos - the index position of the left or right sub-array
     * @param mainArrayPos - the index of the main array
     * @return - the new position of mainArrayPos when finished
     */
    private int copyTheRest(int[] leftOrRightArray, int[] mainArray, int leftOrRightPos, int mainArrayPos) {
        int i = leftOrRightPos;
        int k = mainArrayPos;
        while (i < leftOrRightArray.length) {
            mainArray[k] = leftOrRightArray[i];
            i++;
            k++;
        }
        return k;
    }

    /**
     * Utilizes mergesort to sort and merge an array
     * @param array - the array to be sorted
     * @param first - the position of the first element to be sorted
     * @param last - the position of the last element to be sorted
     */
    public void mergeSort(int[] array, int first, int last) {
        if (first < last) {
            int middle = (first+last)/2;
            mergeSort(array, first, middle);
            mergeSort(array, middle + 1, last);
            merge(array, first, middle, last);
        }
    }
}
