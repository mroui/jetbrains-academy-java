import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Map<String, Integer> wordsOccurrence = new HashMap<>();
        String line = scanner.nextLine().toLowerCase();
        for (String word : line.split("\\s")) {
            wordsOccurrence.put(word, wordsOccurrence.getOrDefault(word, 0) + 1);
        }
        wordsOccurrence.forEach((k, v) -> System.out.println(k + ' ' + v));
    }
}