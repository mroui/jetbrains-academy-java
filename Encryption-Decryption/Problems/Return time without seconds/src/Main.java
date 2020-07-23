import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(LocalTime.parse(scanner.next()).withSecond(0));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}