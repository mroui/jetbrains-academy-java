import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        int month = sc.nextInt();
        LocalDate date1 = LocalDate.of(year, month, 1);
        LocalDate date2 = LocalDate.of(year, month, date1.lengthOfMonth());
        System.out.println(date1 + " " + date2);
    }
}