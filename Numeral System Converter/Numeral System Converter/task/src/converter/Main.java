package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        int sourceRadix = scanner.nextInt();
        String number = scanner.next();
        int targetRadix = scanner.nextInt();
        System.out.println(NumeralConverter.fromToAnyRadix(sourceRadix, number, targetRadix));
        scanner.close();
    }
}
