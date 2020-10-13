import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(checkFixedPointBinarySearch(numbers, 0, size - 1) ? "true" : "false");
    }

    private static boolean checkFixedPointBinarySearch(int[] numbers, int left, int right) {
        if (left > right) {
            return false;
        }
        int mid = left + (right - left) / 2;
        if (mid == numbers[mid]) {
            return true;
        } else if (mid < numbers[mid]) {
            return checkFixedPointBinarySearch(numbers, left, mid - 1);
        } else {
            return checkFixedPointBinarySearch(numbers, mid + 1, right);
        }
    }
}