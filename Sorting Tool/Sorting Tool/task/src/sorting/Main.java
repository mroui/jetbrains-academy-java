package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> numbers = new ArrayList<>();
        while (scanner.hasNextLong()) {
            numbers.add(scanner.nextLong());
        }
        long size = numbers.size();
        long greatest = numbers.stream().max(Long::compareTo).orElse(0L);
        long greatestTimes = numbers.stream().filter(n -> n == greatest).count();
        System.out.println("Total numbers: " + size + '.');
        System.out.println("The largest number: " + greatest + " (" + greatestTimes + " time(s)).");
    }
}
