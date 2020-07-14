package readability;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(countOccurrences(array, scanner.nextInt()));
    }

    public static int countOccurrences(int[] array, int x) {
        int count = 0;
        for (int i : array) {
            if (i == x) {
                count++;
            }
        }
        return count;
    }
}