import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> max = new Stack<>();
        int commandsAmount = scanner.nextInt();
        while (commandsAmount > 0) {
            String command = scanner.next();
            switch (command) {
                case "push":
                    int value = scanner.nextInt();
                    stack.push(value);
                    max.push(stack.size() == 1 || value > max.peek() ? value : max.peek());
                    break;
                case "pop":
                    stack.pop();
                    max.pop();
                    break;
                case "max":
                    System.out.println(max.peek());
            }
            commandsAmount--;
        }
    }
}
