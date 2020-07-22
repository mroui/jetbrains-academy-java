import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate date = LocalDate.ofYearDay(scanner.nextInt(), scanner.nextInt());
            System.out.println(date.getDayOfMonth() == 1);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}