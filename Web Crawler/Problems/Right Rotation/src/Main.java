import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] numbers = scanner.nextLine().split("\\s");
        int shift = scanner.nextInt();
        int mod = shift % numbers.length;
        for (int i = 0; i < numbers.length; i++, mod--) {
            if (mod == 0)
                mod = numbers.length;
            System.out.print(numbers[numbers.length - mod] + " ");
        }
    }
}