import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) - 1 != input.charAt(i - 1)) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }
}