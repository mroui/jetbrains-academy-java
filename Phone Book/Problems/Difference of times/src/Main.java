import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LocalTime time1 = readTime();
        LocalTime time2 = readTime();
        System.out.println(Duration.between(time1, time2).getSeconds());
    }

    private static LocalTime readTime() {
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        int seconds = scanner.nextInt();
        return LocalTime.of(hours, minutes, seconds);
    }
}