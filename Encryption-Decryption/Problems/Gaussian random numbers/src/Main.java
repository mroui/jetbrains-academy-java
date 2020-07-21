import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in).useLocale(Locale.ENGLISH)) {
            int K = scanner.nextInt();
            int N = scanner.nextInt();
            double M = scanner.nextDouble();
            System.out.println(findSeed(K, N, M));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static int findSeed(int K, int N, double M) {
        searching:
        while (true) {
            Random random = new Random(K);
            for (int i = 0; i < N; i++)
                if (random.nextGaussian() > M) {
                    ++K;
                    continue searching;
                }
            break;
        }
        return K;
    }
}