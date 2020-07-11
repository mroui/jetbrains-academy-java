import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int max;
        int min = k;
        int seed = a;

        for (int i = a; i <= b; i++) {
            max = 0;
            Random random = new Random(i);
            for (int j = 0; j < n; j++) {
                int number = random.nextInt(k);
                if (number > max) {
                    max = number;
                }
            }
            if (min > max) {
                min = max;
                seed = i;
            }
        }
        System.out.print(seed + "\n" + min);
    }
}