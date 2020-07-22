import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate date = LocalDate.parse(scanner.next());
            int offset = scanner.nextInt();
            int nextYear = date.getYear() + 1;
            while (date.getYear() != nextYear) {
                System.out.println(date.toString());
                date = date.plusDays(offset);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}