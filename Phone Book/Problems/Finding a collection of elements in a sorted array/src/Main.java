import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] sourceArray = new int[size];
        for (int i = 0; i < size; i++) {
            sourceArray[i] = scanner.nextInt();
        }
        size = scanner.nextInt();
        int[] toFindArray = new int[size];
        for (int i = 0; i < size; i++) {
            toFindArray[i] = scanner.nextInt();
        }
        binarySearch(sourceArray, toFindArray);
    }

    private static void binarySearch(int[] source, int[] toFInd) {
        for (int i : toFInd) {
            System.out.print(binarySearch(source, i, 0, source.length - 1) + " ");
        }
    }

    private static int binarySearch(int[] numbers, int elem, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (elem == numbers[mid]) {
            return mid + 1;
        } else if (elem < numbers[mid]) {
            return binarySearch(numbers, elem, left, mid - 1);
        } else {
            return binarySearch(numbers, elem, mid + 1, right);
        }
    }
}