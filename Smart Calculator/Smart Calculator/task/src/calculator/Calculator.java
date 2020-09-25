package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private static final Map<String, BigInteger> PRECEDENCE_ORDER = Map.of(
            "+", BigInteger.valueOf(1), "-", BigInteger.valueOf(1), "*", BigInteger.valueOf(2),
            "/", BigInteger.valueOf(2), "^", BigInteger.valueOf(3), "(", BigInteger.valueOf(4),
            ")", BigInteger.valueOf(4));
    private static final String ERROR = "ERROR";
    private final Scanner scanner = new Scanner(System.in);
    private final Map<String, BigInteger> variables;
    private boolean running;

    public Calculator() {
        variables = new HashMap<>();
    }

    public void run() {
        running = true;
        out("Welcome in Smart Calculator. Available commands: /help\t/exit\nExample: 2 + 2");
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
            Stack<BigInteger> stack = new Stack<>();
            String[] parts = postfix.split("\\s+");
            for (String part : parts) {
                if (part.matches("-?[0-9]+"))
                    stack.push(new BigInteger(part));
                else if (variables.containsKey(part))
                    stack.push(variables.get(part));
                else stack.push(calculate(part.charAt(0), stack.pop(), stack.pop()));
            }
            out(stack.peek());
        } else out("Invalid expression");
    }

    private BigInteger calculate(char operator, BigInteger y, BigInteger x) {
        switch (operator) {
            case '+':
                return x.add(y);
            case '-':
                return x.subtract(y);
            case '*':
                return x.multiply(y);
            case '/':
                if (y.equals(BigInteger.ZERO)) {
                    out("Division by 0!");
                    return BigInteger.ZERO;
                } else return x.divide(y);
            case '^':
                return power(x, y);
            default:
                out("Unknown operator: " + operator);
                return BigInteger.ZERO;
        }
    }

    private BigInteger power(BigInteger x, BigInteger y) {
        return y.equals(BigInteger.ZERO) ? BigInteger.ONE :
                y.equals(BigInteger.ONE) ? x :
                        x.multiply(power(x, y.subtract(BigInteger.ONE)));
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
                        PRECEDENCE_ORDER.get(part).compareTo(PRECEDENCE_ORDER.get(stack.peek())) > 0) {
                    stack.push(part);
                } else if (!stack.isEmpty() && PRECEDENCE_ORDER.get(part).compareTo(PRECEDENCE_ORDER.get(stack.peek())) < 1) {
                    while (!stack.isEmpty() && (PRECEDENCE_ORDER.get(part).compareTo(PRECEDENCE_ORDER.get(stack.peek())) < 1)
                            && !stack.peek().equals("("))
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
        return equation.replaceAll("\\s+", "").matches("[(]*[a-zA-Z0-9]+([-+]+|[*/^]?)[(]*[A-Za-z0-9]+.*");
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
            variables.put(strings[0], new BigInteger(strings[1]));
        else
            out("Invalid assignment");
    }

    private void handleCommand(String command) {
        switch (command) {
            case "/help":
                out("The calculator support the addition +, subtraction -, division /, multiplications *, power ^ operations." +
                        "\nConsider that the even number of minuses gives a plus." +
                        "\nVariables n = 2 and parentheses (...) are also supported." +
                        "\nOperators and operands must be separated by a space - except parentheses." +
                        "\nThe program should be able to compute something like: 3 + 8 * ((4 + 3) * 2 + 1) --- 6 / (2 ^ 1)");
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
