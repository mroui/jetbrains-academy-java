package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataType dataType = args.length == 2 && args[0].equals("-dataType") ? DataType.valueOf(args[1].toUpperCase()) : DataType.WORD;
        List<String> list = new ArrayList<>();
        switch (dataType) {
            case LONG:
            case WORD:
                while (scanner.hasNext()) {
                    list.add(scanner.next());
                }
                long size = list.size();
                String greatestStr = list.stream().max((s, t) -> {
                    try {
                        return Long.valueOf(s).compareTo(Long.valueOf(t));
                    } catch (NumberFormatException e) {
                        return Integer.compare(s.length(), t.length());
                    }
                }).orElse("");
                long greatestStrTimes = list.stream().filter(s-> s.equals(greatestStr)).count();
                long percentage = greatestStrTimes * 100 / size;
                System.out.println("Total numbers: " + size + '.');
                System.out.println("The greatest number: " + greatestStr + " (" + greatestStrTimes
                        + " time(s), " +  percentage + "%).");
                break;
            case LINE:
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }
                size = list.size();
                greatestStr = list.stream().max(Comparator.comparingInt(String::length)).orElse("");
                greatestStrTimes = list.stream().filter(s -> s.equals(greatestStr)).count();
                percentage = greatestStrTimes * 100 / size;
                System.out.println("Total numbers: " + size + '.');
                System.out.println("The longest line:\n" + greatestStr + "\n(" + greatestStrTimes
                        + " time(s), " +  percentage + "%).");
                break;
        }
    }
}