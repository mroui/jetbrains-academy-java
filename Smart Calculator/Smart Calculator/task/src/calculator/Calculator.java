package calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private static final Map<String, Integer> PRECEDENCE_ORDER = Map.of(
            "+", 1, "-", 1, "*", 2, "/", 2, "(", 3, ")", 3);
    private static final String ERROR = "ERROR";
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
        String postfix = postfix(equation);
        if (!postfix.equals(ERROR)) {
            Stack<Integer> stack = new Stack<>();
            String[] parts = postfix.split("\\s+");
            for (String part : parts) {
                if (part.matches("-?[0-9]+"))
                    stack.push(Integer.parseInt(part));
                else if (variables.containsKey(part))
                    stack.push(variables.get(part));
                else stack.push(calculate(part.charAt(0), stack.pop(), stack.pop()));
            }
            out(stack.peek());
        } else out("Invalid expression");
    }

    private int calculate(char operator, Integer y, Integer x) {
        switch (operator) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                if (y == 0) {
                    out("Division by 0!");
                    return 0;
                } else return x / y;
            default:
                out("Unknown operator: " + operator);
                return 0;
        }
    }

    private String postfix(String equation) {
        StringBuilder result = new StringBuilder();
        String[] parts = prepareToPostfix(equation);
        Stack<String> stack = new Stack<>();
        for (String part : parts) {
            if (part.matches("-?[0-9]+") || variables.containsKey(part))
                result.append(part).append(" ");
            else {
                if (part.equals(")")) {
                    while (!stack.peek().equals("("))
                        result.append(stack.pop()).append(" ");
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek().equals("(") || part.equals("(") ||
                        PRECEDENCE_ORDER.get(part) > PRECEDENCE_ORDER.get(stack.peek())) {
                    stack.push(part);
                } else if (!stack.isEmpty() && PRECEDENCE_ORDER.get(part) <= PRECEDENCE_ORDER.get(stack.peek())) {
                    while (!stack.isEmpty() && (PRECEDENCE_ORDER.get(part) <= PRECEDENCE_ORDER.get(stack.peek())
                            && !stack.peek().equals("(")))
                        result.append(stack.pop()).append(" ");
                    stack.push(part);
                }
            }
        }
        while (!stack.isEmpty()) {
            String item = stack.pop();
            if (!item.matches("[()]"))
                result.append(item).append(" ");
            else {
                result = new StringBuilder(ERROR);
                break;
            }
        }
        return result.toString();
    }

    private String[] prepareToPostfix(String equation) {
        equation = equation.replaceAll("\\(", " ( ");
        equation = equation.replaceAll("\\)", " ) ");
        equation = equation.replaceAll("[+]+", "+");
        String[] parts = equation.trim().split("\\s+");
        for (int i = 0; i < parts.length; i++)
            if (parts[i].contains("-"))
                parts[i] = parts[i].length() % 2 == 0 ? "+" : "-";
        return parts;
    }

    private boolean isEquation(String equation) {
        return equation.replaceAll("\\s+", "").matches("[(]*[a-zA-Z0-9]+([-+]+|[*/]?)[(]*[A-Za-z0-9]+.*");
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
                out("The calculator support the addition +, subtraction -, division /, multiplications * operations." +
                        "\nConsider that the even number of minuses gives a plus." +
                        "\nVariables n = 2 and parentheses (...) are also supported." +
                        "\nThe program should be able to compute something like: 3 + 8 * ((4 + 3) * 2 + 1) --- 6 / (2 + 1)");
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
