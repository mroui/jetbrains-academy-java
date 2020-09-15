import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        String[] numbersStr = scanner.nextLine().split("\\s");
        int wanted = scanner.nextInt();
        for (String s : numbersStr) {
            numbers.add(Integer.parseInt(s));
        }
        List<Integer> numbersMinimum = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (Integer i : numbers) {
            int distance = Math.abs(i - wanted);
            if (distance < minDistance) {
                numbersMinimum.clear();
                numbersMinimum.add(i);
                minDistance = distance;
            } else if (distance == minDistance) {
                numbersMinimum.add(i);
            }
        }
        Collections.sort(numbersMinimum);
        for (Integer i : numbersMinimum) {
            System.out.print(i + " ");
        }
    }
}