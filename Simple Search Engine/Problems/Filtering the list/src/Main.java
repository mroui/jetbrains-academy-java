import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String[] numbers = new Scanner(System.in).nextLine().split("\\s");
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < numbers.length; i += 2)
            list.add(Integer.valueOf(numbers[i]));
        Collections.reverse(list);
        list.forEach(n -> System.out.print(n + " "));
    }
}