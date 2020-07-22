import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate date = LocalDate.parse(scanner.next());
            System.out.println(date.minusYears(30).toString() + '\n' + date.plusYears(30));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}