package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println(NumeralConverter.fromToAnyRadix(scanner.nextInt(), scanner.next(), scanner.nextInt()));
        scanner.close();
    }
}
