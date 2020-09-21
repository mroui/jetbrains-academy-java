package calculator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String[] numbers = scanner.nextLine().split("\\s+");
            int x = Integer.parseInt(numbers[0]);
            int y = Integer.parseInt(numbers[1]);
            System.out.println(x + y);
        } catch (Exception e) {
            System.out.println("Error: " + e + " -> input should looks like: [number] [number]");
        }
    }
}
