import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> first = countOccurrences(scanner.nextLine());
        Map<Character, Integer> second = countOccurrences(scanner.nextLine());
        System.out.println(first.equals(second) ? "yes" : "no");
    }

    private static Map<Character, Integer> countOccurrences(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toLowerCase().toCharArray()) {
            map.put(ch, map.get(ch) == null ? 1 : map.get(ch) + 1);
        }
        return map;
    }
}