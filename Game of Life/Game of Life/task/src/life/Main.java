package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Universe universe = new Universe(scanner.nextInt(), scanner.nextLong());
        scanner.close();
    }
}
