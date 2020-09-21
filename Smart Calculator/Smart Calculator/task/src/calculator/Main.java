package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Map<String, Integer> variables = new HashMap<>();
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
                    // a = 5, a=5, a       = 5, c = a
                    if (input.replaceAll("\\s+", "").matches("[A-Za-z]+[A-Za-z0-9]?+=[A-Za-z0-9]+")) {
                        String[] strings = input.replaceAll("\\s+", "").split("=");
                        if (strings[0].matches(".*[0-9]")) {
                            System.out.println("Invalid identifier");
                        } else if (strings[1].matches("\\D") && variables.get(strings[1]) == null) {
                            System.out.println("Unknown variable");
                        } else if (strings[1].matches("[A-Za-z]+") && variables.get(strings[1]) != null) {
                            variables.put(strings[0], variables.get(strings[1]));
                        } else if (strings[1].matches("\\d+")) {
                            variables.put(strings[0], Integer.parseInt(strings[1]));
                        } else {
                            System.out.println("Invalid assignment");
                        }
                        //a
                    } else if (input.replaceAll("\\s+", "").matches("[A-Za-z0-9]+")) {
                        System.out.println(variables.get(input) != null ? variables.get(input) : "Unknown variable");
                        //1 + 5 - a, ---, ++, b - a
                    } else if (input.replaceAll("\\s+", "").matches("[A-Za-z0-9]+[-+]+[A-Za-z0-9]+.*")) {
                        String[] parts = input.trim().split("\\s+");
                        int result = 0, sign = 1;
                        for (String part : parts) {
                            if (part.matches("[-+]+")) {
                                sign = part.contains("+") ? 1 : part.contains("-") ? part.length() % 2 == 0 ? 1 : -1 : sign;
                            } else if (part.matches("-?[0-9]+")) {
                                result += sign * Integer.parseInt(part);
                            } else if (variables.containsKey(part)) {
                                result += sign * variables.get(part);
                            }
                        }
                        System.out.println(result);
                    } else System.out.println("Invalid identifier");
                }
            } catch (Exception e) {
                System.out.println("Invalid expression");
            }
        }
        System.out.println("Bye!");
    }
}
