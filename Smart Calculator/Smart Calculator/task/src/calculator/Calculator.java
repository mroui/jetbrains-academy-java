package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Calculator {

    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, Integer> variables;
    private boolean running;

    public Calculator() {
        variables = new HashMap<>();
    }

    public void run() {
        running = true;
        while (running) {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                if (input.startsWith("/"))
                    handleCommand(input);
                else handleStatement(input);
            } catch (Exception e) {
                out("Invalid expression");
            }
        }
        out("Bye!");
    }

    private void handleStatement(String statement) {
        if (isAssignment(statement))
            handleVarAssignment(statement);
        else if (isVariable(statement))
            out(variables.get(statement.trim()) != null ? variables.get(statement.trim()) : "Unknown variable");
        else if (isEquation(statement))
            calculate(statement);
        else out("Invalid identifier");
    }

    private void calculate(String equation) {
        String[] parts = equation.trim().split("\\s+");
        int result = 0, sign = 1;
        for (String part : parts) {
            if (part.matches("[-+]+"))
                sign = part.contains("+") ? 1 : part.contains("-") ? part.length() % 2 == 0 ? 1 : -1 : sign;
            else if (part.matches("-?[0-9]+"))
                result += sign * Integer.parseInt(part);
            else if (variables.containsKey(part))
                result += sign * variables.get(part);
        }
        out(result);
    }

    private boolean isEquation(String equation) {
        return equation.replaceAll("\\s+", "").matches("[A-Za-z0-9]+[-+]+[A-Za-z0-9]+.*");
    }

    private boolean isVariable(String equation) {
        return equation.replaceAll("\\s+", "").matches("[A-Za-z0-9]+");
    }

    private boolean isAssignment(String equation) {
        return equation.replaceAll("\\s+", "").matches("[A-Za-z]+[A-Za-z0-9]?+=[-]?[A-Za-z0-9]+");
    }

    private void handleVarAssignment(String equation) {
        String[] strings = equation.replaceAll("\\s+", "").split("=");
        if (strings[0].matches(".*[0-9]"))
            out("Invalid identifier");
        else if (strings[1].matches("\\D") && variables.get(strings[1]) == null)
            out("Unknown variable");
        else if (strings[1].matches("[A-Za-z]+") && variables.get(strings[1]) != null)
            variables.put(strings[0], variables.get(strings[1]));
        else if (strings[1].matches("[-]?\\d+"))
            variables.put(strings[0], Integer.parseInt(strings[1]));
        else
            out("Invalid assignment");
    }

    private void handleCommand(String command) {
        switch (command) {
            case "/help":
                out("The program calculates the sum/subtraction of numbers");
                break;
            case "/exit":
                running = false;
                break;
            default:
                out("Unknown command");
        }
    }

    private void out(Object msg) {
        System.out.println(msg);
    }

}
