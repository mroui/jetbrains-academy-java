import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDateTime dateTime = LocalDateTime.parse(scanner.next());
            int hours = scanner.nextInt();
            int minutes = scanner.nextInt();
            System.out.println(dateTime.minusHours(hours).plusMinutes(minutes));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}