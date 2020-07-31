import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int amount = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < amount; i++) {
            int number = scanner.nextInt();
            sum += number % 6 == 0 ? number : 0;
        }
        System.out.println(sum);
    }
}