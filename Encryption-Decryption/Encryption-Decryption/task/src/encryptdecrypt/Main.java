package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "enc":
                System.out.println(Shift.encrypt(scanner.nextLine(), scanner.nextInt()));
                break;
            case "dec":
                System.out.println(Shift.decrypt(scanner.nextLine(), scanner.nextInt()));
                break;
            case "exit":
                scanner.close();
                return;
            default:
                System.out.println("Unknown operation");
        }
    }
}
