import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int a = scanner.nextInt();
        final int b = scanner.nextInt();
        final Random random = new Random(a + b);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += random.nextInt(b - a + 1) + a;
        }
        System.out.println(sum);
    }
}