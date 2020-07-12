import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] a = new int[size];
        for (int i = 1; i < size; i++)
            a[i] = scanner.nextInt();
        a[0] = scanner.nextInt();
        Arrays.stream(a).forEach(s -> System.out.print(s + " "));
    }
}