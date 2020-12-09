import java.util.Scanner;

public class Main {

    public static long INVERSIONS_COUNTER;

    public static void main(String[] args) {
        INVERSIONS_COUNTER = 0;
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int[] numbers = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            numbers[i] = scanner.nextInt();
        }
        mergeSort(numbers, 0, arraySize);
        System.out.println(INVERSIONS_COUNTER);
    }

    public static void mergeSort(int[] array, int leftIncl, int rightExcl) {
        if (rightExcl <= leftIncl + 1) {
            return;
        }
        int middle = leftIncl + (rightExcl - leftIncl) / 2;
        mergeSort(array, leftIncl, middle);  //sort left subarray
        mergeSort(array, middle, rightExcl); //sort right subarray
        merge(array, leftIncl, middle, rightExcl);
    }

    private static void merge(int[] array, int left, int middle, int right) {
        int i = left;
        int j = middle;
        int k = 0;
        int[] temp = new int[right - left];
        while (i < middle && j < right) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
                INVERSIONS_COUNTER += middle - i;
            }
            k++;
        }
        for (; i < middle; i++, k++) {
            temp[k] = array[i];
        }
        for (; j < right; j++, k++) {
            temp[k] = array[j];
        }
        System.arraycopy(temp, 0, array, left, temp.length);
    }
}