package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println(Encryption.byShift(scanner.nextLine(), scanner.nextInt()));
        scanner.close();
    }
}
