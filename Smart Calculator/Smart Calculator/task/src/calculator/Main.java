package calculator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                if ("/exit".equals(input)) break;
                int[] numbers = Arrays.stream(input.trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                System.out.println(Arrays.stream(numbers).sum());
            } catch (Exception e) {
                System.out.println("Error: " + e + " -> input should looks like: [number] [number]\nTo exit -> /exit");
            }
        }
        System.out.println("Bye!");
    }
}
