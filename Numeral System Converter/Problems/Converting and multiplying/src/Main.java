import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        multiply10list(readInputs());
    }

    private static ArrayList<String> readInputs() {
        final Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        String input;
        while (true) {
            input = scanner.next();
            if (input.equals("0"))
                break;
            list.add(input);
        }
        return list;
    }

    private static void multiply10list(ArrayList<String> list) {
        for (String str : list) {
            try {
                int number = Integer.parseInt(str);
                System.out.printf("%d\n", number * 10);
            } catch (NumberFormatException e) {
                System.out.printf("Invalid user input: %s\n", str);
            }
        }
    }
}