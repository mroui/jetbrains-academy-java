package calculator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                if (input.startsWith("/")) {
                    switch (input) {
                        case "/help":
                            System.out.println("The program calculates the sum/subtraction of numbers");
                            break;
                        case "/exit":
                            running = false;
                            break;
                        default:
                            System.out.println("Unknown command");
                    }
                } else {
                    String[] parts = input.trim().split("\\s+");
                    int result = 0, sign = 1;
                    for (String part : parts) {
                        if(part.matches("[-+]+")){
                            sign = part.contains("+") ? 1 : part.contains("-") ? part.length() % 2 == 0 ? 1 : -1 : sign;
                        } else {
                            result += sign * Integer.parseInt(part);
                        }
                    }
                    System.out.println(result);
                }
            } catch (Exception e) {
                System.out.println("Invalid expression");
            }
        }
        System.out.println("Bye!");
    }
}
