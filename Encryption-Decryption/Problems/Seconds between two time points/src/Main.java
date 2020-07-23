import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalTime from = LocalTime.parse(scanner.next());
            LocalTime to = LocalTime.parse(scanner.next());
            System.out.println(Math.abs(from.toSecondOfDay() - to.toSecondOfDay()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}