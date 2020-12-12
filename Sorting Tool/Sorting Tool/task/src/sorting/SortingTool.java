package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortingTool {

    private ArgumentsType argumentsType;
    private final List<String> data;

    public SortingTool() {
        data = new ArrayList<>();
    }

    public SortingTool(ArgumentsType type) {
        this();
        argumentsType = type;
        readData(type);
    }

    private void readData(ArgumentsType type) {
        Scanner scanner = new Scanner(System.in);
        if (type == ArgumentsType.LINE) {
            while (scanner.hasNextLine())
                data.add(scanner.nextLine());
        } else {
            while (scanner.hasNext())
                data.add(scanner.next());
        }
    }

    public void start() {
        System.out.println("Total numbers: " + data.size() + '.');
        if (argumentsType == ArgumentsType.SORT_INTEGERS)
            calculateSorting();
        else calculateGreatest();
    }

    private void calculateSorting() {
        System.out.print("Sorted data: ");
        data.stream().mapToInt(Integer::parseInt).sorted().forEach(i -> System.out.print(i + " "));
    }

    private void calculateGreatest() {
        String greatest = argumentsType == ArgumentsType.LINE ? calculateMaxLine() : calculateMaxLongOrWord();
        long times = data.stream().filter(s -> s.equals(greatest)).count();
        long percentage = times * 100 / data.size();
        System.out.print(argumentsType == ArgumentsType.LINE
                ? "The longest line:\n" + greatest + "\n("
                : "The greatest number: " + greatest + " (");
        System.out.println(times + " time(s), " + percentage + "%).");
    }

    private String calculateMaxLine() {
        return data.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }

    private String calculateMaxLongOrWord() {
        return data.stream().max((s, t) -> {
            try {
                return Long.valueOf(s).compareTo(Long.valueOf(t));
            } catch (NumberFormatException e) {
                return Integer.compare(s.length(), t.length());
            }
        }).orElse("");
    }
}
