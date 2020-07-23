import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDateTime from = LocalDateTime.parse(scanner.next());
            LocalDateTime to = LocalDateTime.parse(scanner.next());
            if (from.isAfter(to)) {
                LocalDateTime temp = from;
                from = to;
                to = temp;
            }
            int amount = scanner.nextInt();
            int counter = 0;
            for (int i = 0; i < amount; i++) {
                LocalDateTime date = LocalDateTime.parse(scanner.next());
                if (from.isBefore(date) && to.isAfter(date) || from.isEqual(date)) {
                    counter++;
                }
            }
            System.out.println(counter);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}