import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                                      .mapToInt(Integer::parseInt)
                                      .boxed()
                                      .collect(Collectors.toList());
        int swapAmount = scanner.nextInt();
        while (swapAmount-- > 0)
            Collections.swap(numbers, scanner.nextInt(), scanner.nextInt());
        numbers.forEach(e -> System.out.print(e + " "));
    }
}