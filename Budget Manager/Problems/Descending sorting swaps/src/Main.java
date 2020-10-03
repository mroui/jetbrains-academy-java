import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int swapCounter = 0;

    public static void main(String[] args) {
        String[] numbersString = new Scanner(System.in).nextLine().split("\\s+");
        int[] numbers = Arrays.stream(numbersString).mapToInt(Integer::parseInt).toArray();
        bubbleSort(numbers);
        System.out.println(swapCounter);
    }

    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapCounter++;
                }
            }
        }
        return array;
    }
}