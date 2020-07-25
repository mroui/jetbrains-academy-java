package encryptdecrypt;

import java.util.Scanner;
import static encryptdecrypt.EncryptionDecryption.*;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "enc":
                System.out.println(byShift(scanner.nextLine(), scanner.nextInt(), true));
                break;
            case "dec":
                System.out.println(byShift(scanner.nextLine(), scanner.nextInt(), false));
                break;
            case "exit":
                scanner.close();
                return;
            default:
                System.out.println("Unknown operation");
        }
    }
}
