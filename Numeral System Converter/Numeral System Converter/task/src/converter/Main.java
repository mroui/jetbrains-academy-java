package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		System.out.println("[sourceRadix] [number] [targetRadix]");
        Scanner scanner = new Scanner(System.in);
        try {
            int sourceRadix = scanner.nextInt();
            String number = scanner.next();
            int targetRadix = scanner.nextInt();
            if (sourceRadix < 1 || sourceRadix > 36 || targetRadix < 1 || targetRadix > 36)
                throw new NumberFormatException("Radix should be in <1, 35>");
            System.out.println(NumeralConverter.fromToAnyRadix(sourceRadix, number, targetRadix));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
}
