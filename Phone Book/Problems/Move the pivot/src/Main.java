import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void moveThePivot(int[] array, int pivotIndex) {
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, array.length - 1);
        int partitionIndex = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < pivot) {
                swap(array, i, partitionIndex++);
            }
        }
        swap(array, partitionIndex, array.length - 1);
    }

    private static void swap(int[] array, int prevIndex, int newIndex) {
        int temp = array[prevIndex];
        array[prevIndex] = array[newIndex];
        array[newIndex] = temp;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int pivotIndex = scanner.nextInt();
        moveThePivot(array, pivotIndex);
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}