import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int amount = scanner.nextInt();
            if (amount > 0) {
                LocalDateTime latest = LocalDateTime.parse(scanner.next());
                for (int i = 0; i < amount - 1; i++) {
                    LocalDateTime next = LocalDateTime.parse(scanner.next());
                    latest = latest.compareTo(next) < 0 ? next : latest;
                }
                System.out.println(latest);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}