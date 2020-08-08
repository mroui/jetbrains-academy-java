import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String password = new Scanner(System.in).nextLine();
        System.out.println(password.matches(".*[A-Z]+.*") && password.matches(".*[a-z]+.*")
                        && password.matches(".*\\d+.*") && password.length() >= 12 ? "YES" : "NO");
    }
}