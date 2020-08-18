import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        Set<String> sequence = new TreeSet<>();
        while (amount > 0) {
            sequence.add(scanner.next());
            amount--;
        }
        sequence.forEach(System.out::println);
    }
}