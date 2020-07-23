import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalTime time = LocalTime.parse(scanner.next());
            System.out.println(time.minusHours(scanner.nextInt()).minusMinutes(scanner.nextInt()));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}