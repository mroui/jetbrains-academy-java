import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDateTime from = LocalDateTime.parse(scanner.next());
            LocalDateTime to = LocalDateTime.parse(scanner.next());
            System.out.println(LocalDateTime.from(from).until(to, ChronoUnit.HOURS));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}